package com.hana7.springdemo.jpa.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HanaException {
	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
