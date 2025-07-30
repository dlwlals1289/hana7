package com.hana7.springdemo.jpa.controller;

import com.hana7.springdemo.jpa.dto.ReplyRequestDto;
import com.hana7.springdemo.jpa.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;

	@PostMapping("")
	public void insertReply(@RequestBody ReplyRequestDto replyRequestDto){
		replyService.insertReply(replyRequestDto);
	}

}
