package com.hana7.springdemo.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyResponseDto {
	private Long id;
	private String reply;
	private MemberResponseDTO replyer;
}
