package com.hana7.springdemo.jpa.dto;

import com.hana7.springdemo.jpa.entity.BloodType;
import com.hana7.springdemo.jpa.entity.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private Long id;

	@NotNull
	private String nickname;

	@NotBlank
	@Email
	private String email;

	private BloodType bloodType;

	protected Member toEntity() {
		return Member.builder()
				.id(id)
				.nickname(nickname)
				.email(email)
				.bloodType(bloodType)
				.build();
	}
}
