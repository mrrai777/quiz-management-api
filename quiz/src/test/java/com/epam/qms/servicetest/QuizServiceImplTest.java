package com.epam.qms.servicetest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.qms.dto.QuizDTO;
import com.epam.qms.entity.Quiz;
import com.epam.qms.exception.QuestionException;
import com.epam.qms.exception.QuizException;
import com.epam.qms.repository.QuizRepository;
import com.epam.qms.service.QuizServiceImpl;
import com.epam.qms.util.MappingHelper;

class QuizServiceImplTest {

	@Mock
	private QuizRepository quizDao;

	@Mock
	private MappingHelper modelMapper;

	@InjectMocks
	private QuizServiceImpl quizService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateQuizValid() throws QuizException {
		QuizDTO quizDto = mock(QuizDTO.class);
		Quiz quiz = mock(Quiz.class);
		when(modelMapper.convertToQuiz(any())).thenReturn(quiz);
		when(quizDao.save(quiz)).thenReturn(quiz);
		when(modelMapper.convertToQuizDTO(any())).thenReturn(quizDto);

		Assertions.assertEquals(quizDto, quizService.create(quizDto));

		verify(quizDao, times(1)).save(quiz);
	}

	@Test
	void testRemoveQuizValid() throws QuizException {
		doNothing().when(quizDao).deleteById(any());

		quizService.remove(0);

		verify(quizDao).deleteById(any());
	}

	@Test
	void testGetQuizValid() throws QuizException {
		Quiz quiz = mock(Quiz.class);
		QuizDTO quizDto = mock(QuizDTO.class);

		when(quizDao.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
		when(modelMapper.convertToQuizDTO(any())).thenReturn(quizDto);

		quizService.get(0);

		verify(quizDao).findById(0);
		verify(modelMapper).convertToQuizDTO(quiz);
	}

	@Test
	void testGetQuizForException() {
		when(quizDao.findById(0)).thenReturn(Optional.empty());
		Assertions.assertThrowsExactly(QuizException.class, () -> quizService.get(0));
	}

	@Test
	void testModifyQuiz() throws QuestionException, QuizException {

		QuizDTO quizDto = mock(QuizDTO.class);
		Quiz quiz = mock(Quiz.class);
		when(quizDao.save(quiz)).thenReturn(quiz);
		when(modelMapper.convertToQuiz(any())).thenReturn(quiz);
		when(modelMapper.convertToQuizDTO(any())).thenReturn(quizDto);

		QuizDTO result = quizService.modify(quizDto);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(quizDto.getQuizId(), result.getQuizId());
		verify(quizDao, times(1)).save(quiz);
	}

}
