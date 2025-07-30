package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberCustomImplRespository implements MemberCustomRepository {

	private final JPAQueryFactory factory;

	public Page<Member> findAllByNicknameOrEmailContains(String keyword, Pageable pageable) {
		QMember member = QMember.member;

		List<Member> members = factory.selectFrom(member)
				.where(member.nickname.contains(keyword)
						.or(member.email.contains(keyword)))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		Long count = factory.select(member.count())
				.from(member)
				.where(member.nickname.contains(keyword)
						.or(member.email.contains(keyword)))
				.fetchOne();

		if (count == null) {
			count = 0L;
		}
		return new PageImpl<>(members, pageable, count);
	}
}
