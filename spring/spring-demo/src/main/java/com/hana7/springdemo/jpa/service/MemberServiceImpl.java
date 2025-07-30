package com.hana7.springdemo.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import com.hana7.springdemo.jpa.dao.MemberDAO;
import com.hana7.springdemo.jpa.dto.*;

import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.exception.HanaException;
import com.hana7.springdemo.jpa.repository.BoardRepository;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import com.hana7.springdemo.jpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;

	@Override
	public List<MemberDTO> findAll() {

		List<Member> members = memberRepository.findAll();

		if(members.isEmpty()){
			throw new HanaException(HttpStatus.NOT_FOUND, "등록된 멤버가 한 명도 없습니다.");
		}

		return toDtos(members);
	}

	@Override
	public MemberDetailResponseDto findOne(String keyword) {
		Member member = memberRepository.findByNicknameContains(keyword)
				.orElseThrow(()->new HanaException(HttpStatus.NOT_FOUND, keyword+"(이)를 포함한 닉네임을 가진 멤버는 없습니다."));


		return toDetailDto(member);
	}



	@Override
	public MemberDTO save(MemberRequestDTO dto) {
		return null;
	}

	@Override
	public void remove(Long memberId) {
		if(!memberRepository.existsById(memberId)){
			throw new HanaException(HttpStatus.NOT_FOUND, "멤버ID가 " + memberId +"인"+" 멤버가 한 명도 없습니다.");
		}

		memberRepository.deleteById(memberId);
	}

	private List<MemberDTO> toDtos(List<Member> members){
		return members.stream()
				.map((m) -> MemberDTO.builder()
						.id(m.getId())
						.nickname(m.getNickname())
						.email(m.getEmail())
						.bloodType(m.getBloodType())
						.build())
				.toList();
	}

	private MemberDTO toDto(Member m) {
		return MemberDTO.builder()
				.id(m.getId())
				.nickname(m.getNickname())
				.email(m.getEmail())
				.bloodType(m.getBloodType())
				.build();
	}

	private MemberDetailResponseDto toDetailDto(Member member){
		List<Board> boards = member.getBoards();

		List<BoardResponseDTO> list = boards.stream()
				.map((b) -> {
					List<ReplyResponseDto> replyResponseDtos = b.getReplies()
							.stream()
							.map((reply -> ReplyResponseDto.builder()
									.id(reply.getId())
									.replyer(reply.getReplyer().getNickname())
									.reply(reply.getReply())
									.build()))
							.toList();

					return BoardResponseDTO.builder()
							.id(b.getId())
							.title(b.getTitle())
							.writer(b.getWriter().getNickname())
							.hit(b.getHit())
							.createdAt(b.getCreatedAt())
							.replies(replyResponseDtos)
							.build();

				}).collect(Collectors.toList());

		return MemberDetailResponseDto.builder()
				.id(member.getId())
				.nickname(member.getNickname())
				.email(member.getEmail())
				.boards(list)
				.build();
	}
}
