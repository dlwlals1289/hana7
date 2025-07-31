package com.hana7.springdemo.jpa.dto.item;

import com.hana7.springdemo.jpa.entity.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ItemRequestDTO extends ItemDTO {

	public Item toEntity() {
		return Item.builder()
				.itemName(this.getItemName())
				.price(this.getPrice())
				.quantity(this.getQuantity())
				.build();
	}
}
