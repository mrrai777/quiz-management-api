package com.epam.qms.util;

import java.util.List;

import org.mapstruct.Mapper;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.dto.QuizDTO;
import com.epam.qms.dto.UserDTO;
import com.epam.qms.entity.Question;
import com.epam.qms.entity.Quiz;
import com.epam.qms.entity.User;

@Mapper(componentModel = "spring")
public interface MappingHelper {
	Question convertToQuestion(QuestionDTO questionDto);

	QuestionDTO convertToQuestionDTO(Question question);

	Quiz convertToQuiz(QuizDTO quizDto);

	QuizDTO convertToQuizDTO(Quiz quiz);

	UserDTO convertToUserDTO(User user);

	User convertToUser(UserDTO user);
	
	List<QuestionDTO> convertToQuestionDTOs(List<Question> questions);
	
	List<Question> convertToQuestions(List<QuestionDTO> questionDtos);
}
