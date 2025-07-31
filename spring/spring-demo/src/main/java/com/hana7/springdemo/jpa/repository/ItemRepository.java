package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
