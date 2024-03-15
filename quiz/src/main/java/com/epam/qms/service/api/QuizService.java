package com.epam.qms.service.api;

import com.epam.qms.dto.QuizDTO;
import com.epam.qms.exception.QuizException;

public interface QuizService {

	void remove(int quizId);

	QuizDTO get(int quizId) throws QuizException;

	QuizDTO create(QuizDTO quizDto);

	QuizDTO modify(QuizDTO quizDto) throws QuizException;
}
