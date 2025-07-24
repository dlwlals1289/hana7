package com.hana7.springdemo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
//@Builder
public class Post {
	private int id;
	@NotNull
	@Size(min=1, max=50, message = "제목은 1글자 이상 50자 이하 입니다.")
	private String title;

	@NotNull
	private User user;


	private String content;

	@Past
	private LocalDateTime createdAt;
}
