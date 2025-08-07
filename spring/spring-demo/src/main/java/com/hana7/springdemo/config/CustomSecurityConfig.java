package com.hana7.springdemo.config;

<<<<<<< Updated upstream
import com.hana7.springdemo.security.handler.LoginFailureHandler;
import com.hana7.springdemo.security.handler.LoginSuccessHandler;
=======
>>>>>>> Stashed changes
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@Log4j2
public class CustomSecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("--- securityConfig");
		System.out.println("** SecurityConfig.filgerChain");

		http
				// .httpBasic(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.cors(config -> config.configurationSource(corsConfigurationSource()))
				.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin(form -> form
						.loginPage("/api/subscriber/login")
						// .loginProcessingUrl("/api/subscriber/login")
						.successHandler(new LoginSuccessHandler())
						.failureHandler(new LoginFailureHandler())

				);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns(List.of("*"));
		config.setAllowedMethods(List.of(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PATCH.name(),
				HttpMethod.OPTIONS.name(),
				HttpMethod.DELETE.name()));
		config.setAllowedHeaders(List.of(
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CACHE_CONTROL,
				HttpHeaders.CONTENT_TYPE));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
