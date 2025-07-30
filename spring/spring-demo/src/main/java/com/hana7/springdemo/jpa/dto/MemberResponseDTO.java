package com.hana7.springdemo.jpa.dto;

import com.hana7.springdemo.jpa.entity.BloodType;
import com.hana7.springdemo.jpa.entity.Member;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MemberResponseDTO extends MemberDTO {
	MemberResponseDTO(Long id, String nickname, String email, BloodType bloodType) {
		super(id, nickname, email, bloodType);
	}
}
