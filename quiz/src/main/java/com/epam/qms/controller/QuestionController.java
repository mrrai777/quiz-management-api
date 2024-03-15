package com.epam.qms.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.exception.QuestionException;
import com.epam.qms.service.api.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api.qms/questions")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@Operation(summary = "Get Question by ID", description = "Returns a QuestionDTO object")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<QuestionDTO> getQuestion(@PathVariable int id) throws QuestionException {
		QuestionDTO question = questionService.get(id);
		log.info("GET Mapping (getQuestion) : {}", question);
		return ResponseEntity.ok(question);
	}

	@Operation(summary = "Get Questions pagewise", description = "Returns list of QuestionDTO's")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<QuestionDTO>> getQuestions(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize) {
		List<QuestionDTO> questions = questionService.getByPage(pageNo, pageSize);
		log.info("GET Mapping (getQuestions) : {}", questions);

		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@Operation(summary = "Save Question", description = "Return the saved QuestionDTO")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO newQuestion) {
		QuestionDTO question = questionService.create(newQuestion);
		log.info("POST Mapping (createQuestion) : {}", question);

		return new ResponseEntity<>(question, HttpStatus.CREATED);
	}

	@Operation(summary = "Update the Question", description = "Return the updated QuestionDTO")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<QuestionDTO> updateQuestion(@Valid @PathVariable int id,
			@Valid @RequestBody QuestionDTO questionDTO) {
		questionDTO.setQuestionId(id);
		QuestionDTO question = questionService.update(questionDTO);
		log.info("PUT Mapping (updateQuestion) : {}", question);

		return ResponseEntity.ok(question);
	}

	@Operation(summary = "Delete existing Question", description = "Returns nothing")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeQuestion(@PathVariable int id) {
		questionService.remove(id);
		log.info("DELETE Mapping (removeQuestion), Question Id : {}", id);

		return ResponseEntity.noContent().build();
	}
}
