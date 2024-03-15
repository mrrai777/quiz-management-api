package com.epam.qms.controller;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epam.qms.dto.ExceptionDTO;
import com.epam.qms.exception.QuestionException;
import com.epam.qms.exception.QuizException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

	private ExceptionDTO responseBuilder(String message) {
		return ExceptionDTO.builder().message(message).timestamp(LocalDateTime.now()).build();
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionDTO> handleAccessDeniedException(AccessDeniedException exception) {
		log.info("AccessDeniedException : {}", exception.getMessage());
		ExceptionDTO response = responseBuilder(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDTO> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		List<String> info = exception.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.toList();
		log.info("MethodArgumentNotValidException : {}", info);
		ExceptionDTO response = responseBuilder(info.toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionDTO> handleRuntimeException(RuntimeException exception) {
		log.info("RuntimeException : {}", exception.getMessage());
		ExceptionDTO response = responseBuilder(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(QuestionException.class)
	public ResponseEntity<ExceptionDTO> handleQuestionException(QuestionException exception) {
		log.info("QuestionException : {}", exception.getMessage());
		ExceptionDTO response = responseBuilder(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(QuizException.class)
	public ResponseEntity<ExceptionDTO> handleQuizException(QuizException exception) {
		log.info("QuizException : {}", exception.getMessage());
		ExceptionDTO response = responseBuilder(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ExceptionDTO> handleQuizException(UsernameNotFoundException exception) {
		log.info("UsernameNotFoundException : {}", exception.getMessage());
		ExceptionDTO response = responseBuilder(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
