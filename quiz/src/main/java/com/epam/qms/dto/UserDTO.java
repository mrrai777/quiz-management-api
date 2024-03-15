package com.epam.qms.dto;

import java.util.List;

import com.epam.qms.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class UserDTO {
	private String userId;
	@NotNull(message = "userName cannot be null")
	private String username;
	@NotNull(message = "password is mandatory")
	private String password;
	private List<Role> roles;
}
