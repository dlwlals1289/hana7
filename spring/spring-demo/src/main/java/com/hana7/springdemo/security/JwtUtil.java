package com.hana7.springdemo.security;

import com.hana7.springdemo.security.exception.CustomJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
	private static final String KEY = "1234567890123456789012345678901234567890";

	public static String generateToken(Map<String, Object> valueMap, int min) {
		SecretKey key = Keys.hmacShaKeyFor(JwtUtil.KEY.getBytes(StandardCharsets.UTF_8));


		String jwtStr = Jwts.builder().setHeader(Map.of("typ", "JWT"))
				.setClaims(valueMap)
				.setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
				.signWith(key).compact();

		System.out.println("jwtStr = " + jwtStr);
		return jwtStr;
	}

	public static Map<String, Object> validateToke(String token) {
		Map<String, Object> claim = null;
		SecretKey key = null;

		try {
			key = Keys.hmacShaKeyFor(JwtUtil.KEY.getBytes(StandardCharsets.UTF_8));

			claim = Jwts.parserBuilder().setSigningKey(key)
					.build().parseClaimsJwt(token).getBody();
		} catch (WeakKeyException e) {
			throw new CustomJwtException("WeakException");
		}
		return claim;
	}
}
