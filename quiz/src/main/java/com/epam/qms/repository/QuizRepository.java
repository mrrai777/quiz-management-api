package com.epam.qms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.qms.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {

	Optional<Quiz> findByTitle(String title);

}
