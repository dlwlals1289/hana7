package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.*;
import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.exception.HanaException;
import com.hana7.springdemo.jpa.repository.MemberCustomImplRespository;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hana7.springdemo.jpa.service.BoardServiceImpl.toMemberResponseDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;

	private final MemberCustomImplRespository memberCustomImplRespository;

	@Override
	public Page<MemberResponseDTO> findAll(Pageable pageable) {

		Page<Member> members = memberRepository.findAll(pageable);

		if (members.isEmpty()) {
			throw new HanaException(HttpStatus.NOT_FOUND, "등록된 멤버가 한 명도 없습니다.");
		}

		return toDtos(members);
	}

	@Override
	public Page<MemberResponseDTO> findMember(String keyword, Pageable pageable) {
		Page<Member> member = memberCustomImplRespository.findAllByNicknameOrEmailContains(keyword, pageable);

		if (member.isEmpty()) {
			throw new HanaException(HttpStatus.NOT_FOUND, keyword + "(이)를 포함한 닉네임을 가진 멤버는 없습니다.");
		}

		return toDtos(member);
	}

	@Override
	public MemberDetailResponseDto findOne(Long id) {
		Member member = memberRepository.findById(id).orElse(null);

		if (member == null) {
			throw new HanaException(HttpStatus.NOT_FOUND, "멤버ID가 " + id + "인" + " 멤버가 한 명도 없습니다.");
		}

		return toDetailDto(member);
	}


	@Override
	public MemberDTO save(MemberRequestDTO dto) {
		return null;
	}

	@Override
	@Transactional
	public void remove(Long memberId) {
		Member member = memberRepository.findById(memberId).orElse(null);
		if (member == null) {
			throw new HanaException(HttpStatus.NOT_FOUND, "멤버ID가 " + memberId + "인" + " 멤버가 한 명도 없습니다.");
		}

		log.info("----------------- 삭제 전 -----------------");
//		memberRepository.deleteById(memberId);
		memberRepository.delete(member);
		log.info("----------------- 삭제 후 -----------------");

	}

	private Page<MemberResponseDTO> toDtos(Page<Member> members) {
		return members.map((m) -> MemberResponseDTO.builder()
				.id(m.getId())
				.nickname(m.getNickname())
				.email(m.getEmail())
				.bloodType(m.getBloodType())
				.build());
	}

	private MemberDTO toDto(Member m) {
		return MemberDTO.builder()
				.id(m.getId())
				.nickname(m.getNickname())
				.email(m.getEmail())
				.bloodType(m.getBloodType())
				.build();
	}

	private MemberDetailResponseDto toDetailDto(Member member) {
		List<Board> boards = member.getBoards();

		List<BoardResponseDTO> list = boards.stream()
				.map((b) -> {
//					List<ReplyResponseDto> replyResponseDtos = b.getReplies()
//							.stream()
//							.map((reply -> ReplyResponseDto.builder()
//									.id(reply.getId())
//									.replyer(toMemberResponseDTO(reply.getReplyer()))
//									.reply(reply.getReply())
//									.build()))
//							.toList();

					return BoardResponseDTO.builder()
							.id(b.getId())
							.title(b.getTitle())
							.writer(toMemberResponseDTO(b.getWriter()))
							.hit(b.getHit())
							.createdAt(b.getCreatedAt())
//							.replies(replyResponseDtos)
							.repliesCount(b.getReplies().size())
							.build();

				}).collect(Collectors.toList());

		return MemberDetailResponseDto.builder()
				.id(member.getId())
				.nickname(member.getNickname())
				.email(member.getEmail())
				.bloodType(member.getBloodType())
				.boards(list)
				.build();
	}
}
