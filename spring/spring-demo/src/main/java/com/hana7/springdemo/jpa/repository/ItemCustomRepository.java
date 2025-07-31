package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemCustomRepository {
	Page<Item> findAllByItemName(String keyword, Pageable pageable);
}
