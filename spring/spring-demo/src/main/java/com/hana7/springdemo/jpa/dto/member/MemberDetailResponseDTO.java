package com.hana7.springdemo.jpa.dto.member;

import com.hana7.springdemo.jpa.dto.board.BoardDetailResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
public class MemberDetailResponseDTO extends MemberDTO {
	private int auth;
	private List<BoardDetailResponseDTO> boards;
}
