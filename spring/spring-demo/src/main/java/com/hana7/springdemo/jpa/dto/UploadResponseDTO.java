package com.hana7.springdemo.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponseDTO {
	private String saveName;
	private String savedir;
	private boolean isImage;

	public String getLink() {
		return "members/view/" + saveName + "?savedir" + savedir;
	}
}
