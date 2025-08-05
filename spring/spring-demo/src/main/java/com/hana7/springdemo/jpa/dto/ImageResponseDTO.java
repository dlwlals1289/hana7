package com.hana7.springdemo.jpa.dto;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class ImageResponseDTO {
	private Long id;

	private String orgName;

	private File file;
}
