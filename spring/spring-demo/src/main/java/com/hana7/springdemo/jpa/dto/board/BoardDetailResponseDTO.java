package com.hana7.springdemo.jpa.dto.board;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hana7.springdemo.jpa.dto.ReplyResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class BoardDetailResponseDTO extends BoardResponseDTO {
	private String content;

	@JsonManagedReference
	private List<ReplyResponseDTO> replies;
}
