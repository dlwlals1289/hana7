package com.hana7.springdemo.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriberDTO extends User {

	private String email;
	private String nickname;
	private String pwd;
	private boolean social;
	private List<String> roleNames = new ArrayList<>();

	public SubscriberDTO(String email, String nickname, String pwd, boolean social, List<String> roleNames) {
		super(nickname, pwd,
				roleNames.stream().map(SimpleGrantedAuthority::new).toList());

		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.social = social;
		this.roleNames = roleNames;
	}

	public Map<String, Object> getClaims() {
		Map<String, Object> map = new HashMap<>();

		map.put("email", email);
		map.put("pwd", pwd);
		map.put("nickname", nickname);
		map.put("social", social);
		map.put("roleNames", roleNames);

		return map;
	}
}
