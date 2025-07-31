package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.item.ItemRequestDTO;
import com.hana7.springdemo.jpa.dto.item.ItemResponseDTO;
import com.hana7.springdemo.jpa.entity.Item;
import com.hana7.springdemo.jpa.entity.ItemImage;
import com.hana7.springdemo.jpa.exception.NotCreatedException;
import com.hana7.springdemo.jpa.exception.NotFoundException;
import com.hana7.springdemo.jpa.repository.ItemCustomRepository;
import com.hana7.springdemo.jpa.repository.ItemImageRepository;
import com.hana7.springdemo.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemImageRepository itemImageRepository;
	private final ItemCustomRepository itemCustomRepository;

	private static ItemResponseDTO toItemResponseDto(Item item) {
		// ItemImage에서 url만 가져오기
		List<String> imageUrl = item.getItemImages().stream()
				.map(ItemImage::getUrl)
				.toList();

		return ItemResponseDTO.builder()
				.id(item.getId())
				.itemName(item.getItemName())
				.price(item.getPrice())
				.quantity(item.getQuantity())
				.itemImages(imageUrl)
				.build();
	}

	private static ItemResponseDTO toItemResponseDTO(Item item) {
		List<String> urls = item.getItemImages().stream()
				.map(ItemImage::getUrl)
				.toList();

		return ItemResponseDTO.builder()
				.id(item.getId())
				.itemName(item.getItemName())
				.itemImages(urls)
				.price(item.getPrice())
				.quantity(item.getQuantity())
				.build();
	}

	@Override
	@Transactional
	public void addItem(ItemRequestDTO itemRequestDTO) {

		// 아이템 객체 생성하기
		Item item = itemRequestDTO.toEntity();

		// 아이템이미지 객체 생성하고 아이템과 연결하기
		List<ItemImage> itemImages = itemRequestDTO.getItemImages().stream()
				.map((image) -> ItemImage.builder()
						.url(image)
						.item(item)
						.build())
				.collect(Collectors.toList());

		// 아이템 - 아이템이미지 연결하기
		item.toBuilder()
				.itemImages(itemImages)
				.build();

		try {
			itemRepository.save(item);
			itemImageRepository.saveAll(itemImages);
		} catch (RuntimeException e) {
			throw new NotCreatedException(HttpStatus.NOT_ACCEPTABLE, "아이템을 저장할 수 없습니다.");
		}
	}

	@Override
	public Page<ItemResponseDTO> findItems(String keyword, Pageable pageable) {
		Page<Item> items;

		if (keyword == null) {
			items = itemRepository.findAll(pageable);
		} else {
			items = itemCustomRepository.findAllByItemName(keyword, pageable);
		}

		return items.map(ItemServiceImpl::toItemResponseDTO);
	}

	@Override
	public ItemResponseDTO getItem(long itemId) {
		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException("해당 아이디를 가진 아이템을 찾을 수 없습니다."));

		return toItemResponseDto(item);
	}

	@Override
	public HttpStatus updateItem(ItemRequestDTO itemRequestDTO) {
		return null;
	}

	@Override
	public HttpStatus deleteItem(long itemId) {
		return null;
	}
}
