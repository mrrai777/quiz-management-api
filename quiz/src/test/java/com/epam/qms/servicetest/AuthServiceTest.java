package com.epam.qms.servicetest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.qms.dto.UserDTO;
import com.epam.qms.entity.User;
import com.epam.qms.repository.UserRepository;
import com.epam.qms.service.AuthService;
import com.epam.qms.util.MappingHelper;

class AuthServiceTest {

	@Mock
	private UserRepository userDao;

	@Mock
	private MappingHelper modelMapper;

	@InjectMocks
	private AuthService authService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRegister() {
		User user = mock(User.class);
		UserDTO userDto = mock(UserDTO.class);
		when(userDao.findById("Demo")).thenReturn(Optional.empty());
		when(modelMapper.convertToUser(any())).thenReturn(user);
		when(userDao.save(any())).thenReturn(user);

		authService.registerService(userDto);

		verify(userDao).save(any());
	}
}
