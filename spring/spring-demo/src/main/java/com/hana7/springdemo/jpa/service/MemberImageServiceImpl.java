package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.UploadRequestDTO;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.MemberImage;
import com.hana7.springdemo.jpa.repository.MemberImageRepository;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberImageServiceImpl implements MemberImageService {

	private final MemberImageRepository memberImageRepository;
	private final MemberRepository memberRepository;

	@Value("${upload.path}")
	private String uploadPath = "src/main/resources";

	@Override
	@Transactional
	public void addImages(UploadRequestDTO dto) {
		Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();

		if (dto.getFiles() != null) {
			List<MemberImage> images = dto.toEntity(uploadPath);

			images.forEach(image -> image.setMember(member));
			member.getImages().addAll(images);

			try {
				memberImageRepository.saveAll(images);
				memberRepository.save(member);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
