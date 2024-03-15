package com.epam.qms.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDTO {
	private int quizId;
	@NotNull(message = "Quiz Title is mandatory")
	private String title;
	private List<QuestionDTO> questions;
	private int marks;
}
