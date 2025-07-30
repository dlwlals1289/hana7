package com.hana7.springdemo.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder(toBuilder = true)
@Getter @Setter
public class BoardDetailResponseDTO extends BoardResponseDTO{
	private String content;

	private List<ReplyResponseDto> replyResponseDto;
}
