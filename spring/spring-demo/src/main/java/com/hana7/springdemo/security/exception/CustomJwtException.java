package com.hana7.springdemo.security.exception;

public class CustomJwtException extends RuntimeException {
<<<<<<< Updated upstream
	public CustomJwtException(String msg) {
		super("JwtErr:" + msg);
=======
	public CustomJwtException(String message) {

		super("JWTErr : " + message);
>>>>>>> Stashed changes
	}
}
