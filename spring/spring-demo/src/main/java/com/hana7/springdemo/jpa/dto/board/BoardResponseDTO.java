package com.hana7.springdemo.jpa.dto.board;

import com.hana7.springdemo.jpa.dto.member.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
public class BoardResponseDTO {
	private int id;
	private String title;
	private MemberDTO writer;
	private int hit;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// private String content;
}
