package com.hana7.springdemo.jpa.controller;

import com.hana7.springdemo.jpa.dto.item.ItemRequestDTO;
import com.hana7.springdemo.jpa.dto.item.ItemResponseDTO;
import com.hana7.springdemo.jpa.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;

	@PostMapping("")
	public ResponseEntity<?> addItem(@RequestBody ItemRequestDTO itemRequestDTO) {
		itemService.addItem(itemRequestDTO);

		return ResponseEntity.ok().build();
	}

	@GetMapping("{id}")
	public ItemResponseDTO getItem(@PathVariable long id) {
		return itemService.getItem(id);
	}

	@GetMapping("")
	public Page<ItemResponseDTO> findItem(
			@RequestParam(required = false) String keyword,
			@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		return itemService.findItems(keyword, pageable);
	}

}
