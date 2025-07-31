package com.hana7.springdemo.jpa.dto.member;

import com.hana7.springdemo.jpa.entity.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MemberRequestDTO extends MemberDTO {
	private String passwd;

	protected Member toEntity() {
		Member mbr = super.toEntity();
		mbr.setPasswd(passwd);

		return mbr;
	}
}
