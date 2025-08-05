package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dao.MemberDAO;
import com.hana7.springdemo.jpa.dto.*;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.MemberImage;
import com.hana7.springdemo.jpa.repository.MemberImageRepository;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
	private final MemberDAO dao;
	private final MemberRepository memberRepository;
	private final MemberImageRepository memberImageRepository;

	@Value("${upload.path}")
	private String uploadPath = "src/main/resources";

	public static MemberDTO toDTO(Member member) {
		return MemberResponseDTO.builder()
				.id(member.getId())
				.nickname(member.getNickname())
				.email(member.getEmail())
				.bloodType(member.getBloodType())
				.build();
	}

	static MemberDTO toDetailDTO(Member member, List<ImageResponseDTO> images) {

		return MemberDetailResponseDTO.builder()
				.id(member.getId())
				.nickname(member.getNickname())
				.email(member.getEmail())
				.bloodType(member.getBloodType())
				.auth(member.getAuth())
				.boards(member.getBoards().stream().map(BoardServiceImpl::toDetailDTO).toList())
				.images(images)
				.build();
	}

	@Override
	public List<MemberDTO> findAll(SearchCond searchCond) {
		List<Member> members;
		if (searchCond.needSearch()) {
			members = dao.findAll(searchCond);
		} else {
			members = dao.findAll(searchCond.getPager());
		}

		return members.stream()
				.map(MemberServiceImpl::toDTO).toList();
	}

	@Override
	public MemberDTO findOne(long id) {
		Member member = memberRepository.findById(id).orElseThrow();
		List<MemberImage> images = memberImageRepository.findAllByMember(member);

		System.out.println(images.size());

		List<ImageResponseDTO> imageDtos = images.stream()
				.map((image) -> {
					Resource resource = new FileSystemResource(uploadPath + File.separator + image.getSaveName());
					try {
						return ImageResponseDTO.builder()
								.id(image.getId())
								.orgName(image.getOrgName())
								.file(resource.getFile())
								.build();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				})
				.toList();

		System.out.println(imageDtos);
		return toDetailDTO(member, imageDtos);
//		return toDetailDTO(dao.findOne(id));
	}

	@Override
	public int remove(long id) {
		log.debug("id={}", id);
		return dao.remove(id);
	}
}
