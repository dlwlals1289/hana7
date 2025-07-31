package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Item;
import com.hana7.springdemo.jpa.entity.ItemImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Rollback(false)
class ItemRepositoryTest extends RepositoryTest {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemImageRepository itemImageRepository;

	@Autowired
	private ItemCustomRepository itemCustomRepository;

	@Test
	void addItem() {
		// given
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

		item.toBuilder().itemImages(itemImages);


		// when
		Item saveItem = itemRepository.save(item);
		itemImageRepository.saveAll(itemImages);

		// then
		Assertions.assertThat(saveItem.getItemName()).isEqualTo(item.getItemName());
	}

	// TODO : 뭐가 잘못된걸까
//	@Test
//	void findItemWithKeyword() {
//		// given
//		Item item1 = Item.builder()
//				.itemName("사과")
//				.price(1000)
//				.quantity(10)
//				.build();
//
//		Item item2 = Item.builder()
//				.itemName("청사과")
//				.price(1000)
//				.quantity(10)
//				.build();
//
//		Item item3 = Item.builder()
//				.itemName("포도")
//				.price(1000)
//				.quantity(10)
//				.build();
//
//		List<Item> items = List.of(item1, item2, item3);
//		itemRepository.saveAll(items);
//
//		String keyword = "사과";
//
//		// when
//		Page<Item> findItems = itemCustomRepository.findAllByItemName(keyword, PageRequest.of(0, 10));
//
//		// then
//
//		Assertions.assertThat(findItems.getTotalElements()).isEqualTo(2);
//	}


}
