package com.hana7.springdemo.jpa.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ItemResponseDTO extends ItemDTO {
	private long id;
}
