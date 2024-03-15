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
public class QuestionDTO {
	private int questionId;
	@NotNull(message = "Question Description is required field")
	private String description;
	@NotNull(message = "Question Options are required")
	private List<String> options;
	@NotNull(message = "Question Difficulty is mandatory")
	private String difficulty;
	@NotNull(message = "Question Topic is required field")
	private String topic;
	@NotNull(message = "Please provide the answer for the question")
	private String answer;
	@NotNull(message = "Provide Marks for question")
	private Integer marks;
}
