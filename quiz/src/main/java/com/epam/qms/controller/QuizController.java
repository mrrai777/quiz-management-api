package com.epam.qms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.qms.dto.QuizDTO;
import com.epam.qms.exception.QuizException;
import com.epam.qms.service.api.QuizService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api.qms/quizzes")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<QuizDTO> getQuiz(@PathVariable int id) throws QuizException {
		QuizDTO quiz = quizService.get(id);
		log.info("GET Mapping (getQuiz) : {}", quiz);

		return ResponseEntity.ok(quiz);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<QuizDTO> createQuiz(@Valid @RequestBody QuizDTO quizDto) {
		QuizDTO quiz = quizService.create(quizDto);
		log.info("POST Mapping (createQuiz) : {}", quizDto);

		return new ResponseEntity<>(quiz, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<QuizDTO> updateQuiz(@PathVariable int id, @Valid @RequestBody QuizDTO quizDTO)
			throws QuizException {
		quizDTO.setQuizId(id);
		QuizDTO quiz = quizService.modify(quizDTO);
		log.info("PUT Mapping (updateQuiz) : {}", quiz);

		return new ResponseEntity<>(quiz, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Void> removeQuiz(@PathVariable int id) {
		quizService.remove(id);
		log.info("DELETE Mapping (removeQuiz) : {}", id);

		return ResponseEntity.noContent().build();
	}
}
