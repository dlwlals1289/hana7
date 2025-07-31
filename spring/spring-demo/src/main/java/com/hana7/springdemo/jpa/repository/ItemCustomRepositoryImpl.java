package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Item;
import com.hana7.springdemo.jpa.entity.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemCustomRepositoryImpl implements ItemCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public ItemCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public Page<Item> findAllByItemName(String keyword, Pageable pageable) {
		QItem qItem = QItem.item;

		List<Item> item = jpaQueryFactory.selectFrom(qItem)
				.where(qItem.itemName.contains(keyword))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		Long count = jpaQueryFactory.select(qItem.count())
				.from(qItem)
				.where(qItem.itemName.contains(keyword))
				.fetchOne();

		if (count == null) {
			count = 0L;
		}

		return new PageImpl<>(item, pageable, count);
	}
}
