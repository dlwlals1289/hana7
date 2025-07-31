package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.item.ItemRequestDTO;
import com.hana7.springdemo.jpa.dto.item.ItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

public interface ItemService {
	void addItem(ItemRequestDTO itemRequestDTO);

	Page<ItemResponseDTO> findItems(String keyword, Pageable pageable);

	ItemResponseDTO getItem(long itemId);

	HttpStatus updateItem(ItemRequestDTO itemRequestDTO);

	HttpStatus deleteItem(long itemId);
}
