package com.hana7.springdemo.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyRequestDto {
	private String reply;
	private Long replyerId;
	private int boardId;
}
