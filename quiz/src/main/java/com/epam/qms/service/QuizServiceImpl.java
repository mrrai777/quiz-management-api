package com.epam.qms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.dto.QuizDTO;
import com.epam.qms.exception.QuizException;
import com.epam.qms.repository.QuizRepository;
import com.epam.qms.service.api.QuizService;
import com.epam.qms.util.Constants;
import com.epam.qms.util.MappingHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private MappingHelper mapper;

	@Override
	public QuizDTO create(QuizDTO quizDto) {
		log.info("Create Quiz with data : {}", quizDto);
		quizDto.setMarks(countQuizMarks(quizDto.getQuestions()));
		return mapper.convertToQuizDTO(quizRepository.save(mapper.convertToQuiz(quizDto)));
	}

	@Override
	public void remove(int id) {
		log.info("Remove Quiz with Quiz Id : {}", id);
		quizRepository.deleteById(id);
	}

	@Override
	public QuizDTO get(int id) throws QuizException {
		log.info("Get Quiz with Quiz Id : {}", id);
		return quizRepository.findById(id).map(mapper::convertToQuizDTO)
				.orElseThrow(() -> new QuizException(Constants.INVALID_QUIZ_ID));
	}

	@Override
	public QuizDTO modify(QuizDTO quizDto) throws QuizException {
		log.info("Modify Quiz with data : {}", quizDto);
//		Quiz quiz = quizRepository.findById(quizDto.getId())
//				.orElseThrow(() -> new QuizException("No Quiz present with given ID"));
//
//		updateQuestionsList(quizDto, quiz);

		return mapper.convertToQuizDTO(quizRepository.save(mapper.convertToQuiz(quizDto)));
	}

	/*
	 * private void updateQuestionsList(QuizDTO quizDto, Quiz quiz) { if
	 * (Objects.isNull(quizDto.getQuestions())) {
	 * quizDto.setQuestions(mapper.convertToQuestionDTOs(quiz.getQuestions())); }
	 * else {
	 * quizDto.getQuestions().addAll(mapper.convertToQuestionDTOs(quiz.getQuestions(
	 * ))); } }
	 */

	private Integer countQuizMarks(List<QuestionDTO> questions) {
		return questions.stream().mapToInt(QuestionDTO::getMarks).sum();
	}

}
