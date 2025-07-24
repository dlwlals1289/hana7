package com.hana7.springdemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class User {
	private int id;

	@NotNull
	@Size(min=2, max=50, message="이름은 2글자 이상 50자 이하입니다")
	private String name;

	@NotBlank
	@Email
	private String email;

	@Pattern(regexp = "^010-\\d{3,4}-\\d{4}", message="올바른 전화번호 형식이 아님")
	private String mobile;
}
