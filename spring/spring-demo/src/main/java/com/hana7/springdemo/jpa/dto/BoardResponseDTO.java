package com.hana7.springdemo.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class BoardResponseDTO {
	private int id;
	private String title;
	private MemberResponseDTO writer;
	private int hit;
	private LocalDateTime createdAt;
	//	private List<ReplyResponseDto> replies;
	private long repliesCount;
}
