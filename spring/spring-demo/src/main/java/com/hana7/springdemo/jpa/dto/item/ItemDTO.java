package com.hana7.springdemo.jpa.dto.item;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
public class ItemDTO {

	private String itemName;

	private int price;

	private int quantity;

	private List<String> itemImages;

}
