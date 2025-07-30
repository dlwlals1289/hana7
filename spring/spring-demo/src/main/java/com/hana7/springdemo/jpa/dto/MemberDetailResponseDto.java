package com.hana7.springdemo.jpa.dto;

import com.hana7.springdemo.jpa.entity.BloodType;
import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.Reply;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberDetailResponseDto {
	private Long id;

	@NotNull
	private String nickname;

	@NotBlank
	@Email
	private String email;

	private BloodType bloodType;

	private List<BoardResponseDTO> boards;

}
