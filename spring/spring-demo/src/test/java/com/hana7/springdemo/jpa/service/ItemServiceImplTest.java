package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.item.ItemRequestDTO;
import com.hana7.springdemo.jpa.dto.item.ItemResponseDTO;
import com.hana7.springdemo.jpa.entity.Item;
import com.hana7.springdemo.jpa.entity.ItemImage;
import com.hana7.springdemo.jpa.repository.ItemImageRepository;
import com.hana7.springdemo.jpa.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
@Rollback(false)
class ItemServiceImplTest {
	private static long itemId;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemImageRepository itemImageRepository;
	@Autowired
	private ItemService itemService;

	@BeforeEach
	void beforeEach() {
		Item item = Item.builder()
				.itemName("item1")
				.price(1000)
				.quantity(10)
				.build();

		List<ItemImage> itemImages = Stream.iterate(1, n -> n + 1)
				.limit(5)
				.map(n -> ItemImage.builder()
						.url("url" + n)
						.item(item)
						.build())
				.collect(Collectors.toList());

		item.getItemImages().addAll(itemImages);

		Item saved = itemRepository.save(item);
		itemImageRepository.saveAll(itemImages);

		itemId = saved.getId();
	}


	@Test
	void addItem() {
		//given
		ItemRequestDTO itemRequestDTO = ItemRequestDTO.builder()
				.itemName("사과")
				.price(9900)
				.quantity(10)
				.itemImages(List.of("url1", "url2"))
				.build();

		// when
		itemService.addItem(itemRequestDTO);


//		//then
//		Assertions.assertThat(httpStatus).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void findItems() {
		//given
		Item item1 = Item.builder()
				.itemName("사과")
				.price(1000)
				.quantity(10)
				.build();

		Item item2 = Item.builder()
				.itemName("청사과")
				.price(1000)
				.quantity(10)
				.build();

		Item item3 = Item.builder()
				.itemName("포도")
				.price(1000)
				.quantity(10)
				.build();

		List<Item> items = List.of(item1, item2, item3);
		itemRepository.saveAll(items);

		String keyword = "사과";

		//when
		Page<ItemResponseDTO> findItems1 = itemService.findItems(keyword, PageRequest.of(0, 10));
		Page<ItemResponseDTO> findItems2 = itemService.findItems(null, PageRequest.of(0, 10));

		// then
		Assertions.assertThat(findItems1.getTotalElements()).isEqualTo(2L);
		Assertions.assertThat(findItems2.getTotalElements()).isEqualTo(4L);
	}

	@Test
	void getItem() {
		//given
		Item originalItem = itemRepository.findById(itemId).orElseThrow();
		System.out.println(originalItem.getItemImages());

		// when
		ItemResponseDTO item = itemService.getItem(itemId);

		//then
		Assertions.assertThat(item.getId()).isEqualTo(originalItem.getId());
		Assertions.assertThat(item.getItemName()).isEqualTo(originalItem.getItemName());
	}

	@Test
	void updateItem() {
	}

	@Test
	void deleteItem() {
	}
}
