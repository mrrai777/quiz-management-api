package com.epam.qms.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int roleId;
	@Column(name = "role_name")
	private String roleName;
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	List<User> users;

	public Role(String roleName) {
		this.roleName = roleName;
	}
}
