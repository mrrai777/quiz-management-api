package com.epam.qms.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.qms.dto.UserDTO;
import com.epam.qms.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

	@MockBean
	private AuthService authService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Mock
	private AuthenticationManager authenticationManager;

	@Test
	void testLoginUser() throws Exception {
		Authentication authentication = mock(Authentication.class);
		UserDTO userDto = UserDTO.builder().username("abc").password("xyz").build();
		when(authService.generateToken(any())).thenReturn("token");
		when(authenticationManager.authenticate(any())).thenReturn(authentication);

		mockMvc.perform(post("/api.qms/users/login").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userDto)));

		verify(authService).generateToken(any());
	}

	@Test
	void testRegisterUser() throws Exception {
		UserDTO userDto = UserDTO.builder().username("user").password("password").build();
		doNothing().when(authService).registerService(any());

		mockMvc.perform(post("/api.qms/users/register").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userDto)));
		verify(authService).registerService(userDto);
	}
}
