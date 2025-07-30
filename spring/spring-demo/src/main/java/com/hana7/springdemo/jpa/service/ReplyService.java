package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.ReplyRequestDto;


public interface ReplyService {
	void insertReply(ReplyRequestDto replyRequestDto);
}
