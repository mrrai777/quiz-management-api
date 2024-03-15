package com.epam.qms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	private String description;

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "id"))
	@Column(unique = false, nullable = true)
	private List<String> options;
	private String difficulty;
	private String topic;
	private String answer;
	private int marks;

	@JsonIgnore
	@ManyToMany(mappedBy = "questions")
	private List<Quiz> quizzes;

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public List<Quiz> getQuizzes() {
		return this.quizzes;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String tags) {
		this.topic = tags;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Que : \nQuestion Id = " + questionId + "\nQuestion Description = " + description + "\nQuestion Options = "
				+ options + "\nQuestion Difficulty = " + difficulty + "\nTags = " + topic + "\nQuestionAnswer = "
				+ answer + "\nQuestion Marks = " + marks;
	}
}
