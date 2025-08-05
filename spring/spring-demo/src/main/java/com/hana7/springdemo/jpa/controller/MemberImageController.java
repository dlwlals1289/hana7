package com.hana7.springdemo.jpa.controller;

import com.hana7.springdemo.jpa.dto.UploadRequestDTO;
import com.hana7.springdemo.jpa.service.MemberImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class MemberImageController {
	private final MemberImageService memberImageService;

	@Value("${upload.path}")
	private String uploadPath = "src/main/resources";

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> upload(UploadRequestDTO dto) {
		memberImageService.addImages(dto);

		return ResponseEntity.ok("MemberImage 업로드를 성공했습니다!");
	}
}
