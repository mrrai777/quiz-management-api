package com.epam.qms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.qms.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	Optional<User> findByUsername(String username);
}
