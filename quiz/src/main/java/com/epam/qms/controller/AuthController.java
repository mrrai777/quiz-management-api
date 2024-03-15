package com.epam.qms.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.qms.dto.UserDTO;
import com.epam.qms.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api.qms/users")
public class AuthController {

	private final AuthService authService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("register")
	public void register(@RequestBody UserDTO userDTO) {
		authService.registerService(userDTO);
	}

	@PostMapping("login")
	public String login(@RequestBody UserDTO userLogin) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
		log.info("Token requested for user :{}", authentication.getAuthorities());

		return authService.generateToken(authentication);
	}
}