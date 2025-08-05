package com.hana7.springdemo.jpa.service;


import com.hana7.springdemo.jpa.dto.UploadRequestDTO;

public interface MemberImageService {

	void addImages(UploadRequestDTO uploadRequestDTO);
}
