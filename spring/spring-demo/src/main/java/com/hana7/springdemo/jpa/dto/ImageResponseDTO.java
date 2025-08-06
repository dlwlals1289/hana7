package com.hana7.springdemo.jpa.dto;

import com.hana7.springdemo.jpa.entity.MemberImage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponseDTO {
	private Long id;

	private String orgName;

	private String saveName;

	private String savedir;

	public String getLink() {
		return "members/view/" + saveName + "?savedir" + savedir;
	}

	public MemberImage toEntity() {
		return MemberImage.builder()
				.orgName(orgName)
				.saveName(saveName)
				.saveDir(savedir)
				.build();
	}

}
