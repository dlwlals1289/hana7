package com.hana7.springdemo.jpa.exception;

import org.springframework.http.HttpStatus;

public class NotCreatedException extends HanaException {
	public NotCreatedException(HttpStatus httpStatus, String message) {
		super(httpStatus, message);
	}
}
