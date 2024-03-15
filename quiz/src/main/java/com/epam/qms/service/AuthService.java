package com.epam.qms.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.epam.qms.dto.UserDTO;
import com.epam.qms.entity.Role;
import com.epam.qms.entity.User;
import com.epam.qms.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private JwtEncoder jwtEncoder;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now)
				.expiresAt(now.plus(10, ChronoUnit.HOURS)).subject(authentication.getName()).claim("scope", scope)
				.build();

		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	public void registerService(UserDTO userDto) {
		User user = User.builder().username(userDto.getUsername())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.roles(List.of(Role.builder().roleName("USER").build())).build();
		userRepository.save(user);
	}
}
