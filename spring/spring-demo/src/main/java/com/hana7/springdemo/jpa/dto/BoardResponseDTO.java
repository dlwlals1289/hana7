package com.hana7.springdemo.jpa.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter @Setter
public class BoardResponseDTO {
	private int id;
	private String title;
	private String writer;
	private int hit;
	private LocalDateTime createdAt;
	private List<ReplyResponseDto> replies;

}
