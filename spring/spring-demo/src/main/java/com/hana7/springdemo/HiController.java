package com.hana7.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HiController {
	@GetMapping("/hi")
	public String hi(String name) {
		log.debug("Debug={}", name);
		log.info("Hi! Info!!");
		return "Hi " + name + "!!";
	}

	@GetMapping("/hi/{time}")
	public String hiName(@PathVariable("time") String time){
		log.debug("hiName={}", time);
		return "Good" + time;
	}
}

