package com.hana7.springdemo.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 31, nullable = false)
	private String itemName;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private int quantity;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	@Builder.Default
	@ToString.Exclude
	private List<ItemImage> itemImages = new ArrayList<>();


}
