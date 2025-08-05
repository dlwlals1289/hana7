package com.hana7.springdemo.jpa.controller;

import com.hana7.springdemo.jpa.dto.MemberDTO;
import com.hana7.springdemo.jpa.dto.SearchCond;
import com.hana7.springdemo.jpa.dto.UploadRequestDTO;
import com.hana7.springdemo.jpa.dto.UploadResponseDTO;
import com.hana7.springdemo.jpa.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberService service;

	@Value("${upload.path}")
	private String uploadPath = "src/main/resources";

	@Tag(name = "file upload")
	@Operation(summary = "upload POST Member")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<UploadResponseDTO> upload(UploadRequestDTO dto) {
		log.info("upfileDto={}", dto);
		log.info("isItNull={}", dto.getFiles());
		log.info("uploadPath={}", uploadPath);
		List<UploadResponseDTO> upfiles = new ArrayList<>();

		if (dto.getFiles() != null) {
			dto.getFiles().forEach(file -> {
				String orgFname = file.getOriginalFilename();
				log.info("orgName={}", orgFname + "::" + file.getSize() / 1024);
				String uuid = UUID.randomUUID().toString();
				String savedFname = uuid + "_" + orgFname;
				Path upfilePath = Paths.get(uploadPath, savedFname);
				try {
					Path uploadDir = Paths.get(uploadPath);
					if (!Files.exists(uploadDir)) {
						Files.createDirectories(uploadDir);
					}
					file.transferTo(upfilePath);

					boolean isImage = Files.probeContentType(upfilePath).startsWith("image");
					if (isImage) {
						File thumbnail = new File(uploadPath, "thumb_" + uuid + "_" + orgFname);
						Thumbnailator.createThumbnail(upfilePath.toFile(), thumbnail, 200, 200);
					}

					upfiles.add(UploadResponseDTO.builder()
							.orgFname(orgFname)
							.fname(savedFname)
							.isImage(isImage)
							.build());

				} catch (IOException e) {
					log.error(e.getMessage());
					throw new RuntimeException(e);
				}
			});
		}

		return upfiles;
	}

	@Tag(name = "Download File")
	@Operation(summary = "Download Image")
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFile(@PathVariable String fileName) throws IOException {
		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()));

		return ResponseEntity.ok().headers(headers).body(resource);
	}

	@Tag(name = "Delete File")
	@Operation(summary = "Delete Image")
	@GetMapping("/delete/{fileName}")
	public Map<String, Boolean> deleteFile(@PathVariable String fileName) throws IOException {
		Map<String, Boolean> resMap = new HashMap<>();

		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		resMap.put(resource.getFile().getName(), resource.getFile().delete());
		if (Files.probeContentType(resource.getFile().toPath()).startsWith("image")) {
			File thumbnail = new File(uploadPath, "thumb_" + fileName);
			resMap.put(thumbnail.getName(), thumbnail.delete());
		}
		System.out.println("resMap = " + resMap);
		return resMap;
	}

	@GetMapping()
	@Tag(name = "회원 목록", description = "회원 페이징 목록")
	@Operation(summary = "/members?page=1...", description = "회원목록")
	List<MemberDTO> findMembers(
			@Parameter(example = """
					{
						"page": 1,
						"size": 5,
						"searchNickname": "x",
						"searchEmail:: null,
						"sortField": "id",
						"sortDirection" : "asc"
					}
					""")
			SearchCond searchCond) {
		System.out.println("searchCond = " + searchCond.getPager());
		return service.findAll(searchCond);
	}

	@GetMapping("{id}")
	@Tag(name = "회원 상세")
	@Operation(summary = "/members/2", description = "회원 작성된 글 포함")
	@Parameters({
			@Parameter(name = "id", description = "회원 번호", example = "2"),
	})
	MemberDTO getMember(@PathVariable Long id) {
		return service.findOne(id);
	}

	@DeleteMapping("{id}")
	ResponseEntity<?> remove(@PathVariable Long id) {
		long ret = service.remove(id);
		if (ret > 0)
			return ResponseEntity.ok(ret);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + "is Not Found!");
	}
}
