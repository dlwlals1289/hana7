package com.hana7.springdemo.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BoardRequestDTO {
	private int id;

	@NotBlank
	@Size(min = 1, max = 40)
	private String title;

	private long writerId;

	@NotBlank
	private String content;
}
