package com.hana7.springdemo.jpa.dto;

import com.hana7.springdemo.jpa.entity.BloodType;
import com.hana7.springdemo.jpa.entity.Member;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class MemberResponseDTO extends MemberDTO {
	MemberResponseDTO(Long id, String nickname, String email, BloodType bloodType) {
		super(id, nickname, email, bloodType);
	}
}
