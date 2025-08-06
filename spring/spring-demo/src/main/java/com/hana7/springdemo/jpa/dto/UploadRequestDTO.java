package com.hana7.springdemo.jpa.dto;

import com.hana7.springdemo.jpa.entity.MemberImage;
import lombok.Data;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
public class UploadRequestDTO {
	private Long memberId;
	private List<MultipartFile> files;


	public List<MemberImage> toEntity(String uploadPath) {
		LocalDateTime now = LocalDateTime.now();
		String newPath = uploadPath + File.separator + String.format("%4d/%02d/%02d", now.getYear(),
				now.getMonthValue(), now.getDayOfMonth());

		return this.files.stream().map(file -> {
			String orgFname = file.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String savedFname = uuid + "_" + orgFname;
			Path upfilePath = Paths.get(newPath, savedFname);

			try {
				Path uploadDir = Paths.get(newPath);
				if (!Files.exists(uploadDir)) {
					Files.createDirectories(uploadDir);
				}
				file.transferTo(upfilePath);

				boolean isImage = Files.probeContentType(upfilePath).startsWith("image");
				if (isImage) {
					File thumbnail = new File(newPath, "thumb_" + uuid + "_" + orgFname);
					Thumbnailator.createThumbnail(upfilePath.toFile(), thumbnail, 200, 200);
				}

				return MemberImage.builder()
						.orgName(orgFname)
						.saveName(savedFname)
						.saveDir(uploadDir.toString())
						.build();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).toList();


	}
}

