package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
}
