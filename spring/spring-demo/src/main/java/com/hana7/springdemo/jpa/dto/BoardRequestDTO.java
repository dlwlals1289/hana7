package com.hana7.springdemo.jpa.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class BoardRequestDTO {
	private int id;

	@NotBlank
	@Size(min = 1, max = 40)
	private String title;

	@NotBlank
	@Size(min = 1, max = 30)
	private long writerId;

	@NotBlank
	private String content;
}
