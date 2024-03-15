package com.epam.qms.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.entity.Question;
import com.epam.qms.exception.QuestionException;
import com.epam.qms.repository.QuestionRepository;
import com.epam.qms.service.QuestionServiceImpl;
import com.epam.qms.util.MappingHelper;

class QuestionServiceImplTest {

	@Mock
	private QuestionRepository questionDao;

	@Mock
	private MappingHelper modelMapper;

	@InjectMocks
	private QuestionServiceImpl questionService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateQuestion() throws QuestionException {

		QuestionDTO questionDto = mock(QuestionDTO.class);
		Question question = mock(Question.class);
		when(modelMapper.convertToQuestion(any())).thenReturn(question);
		when(questionDao.save(question)).thenReturn(question);
		when(modelMapper.convertToQuestionDTO(any())).thenReturn(questionDto);

		Assertions.assertEquals(questionDto, questionService.create(questionDto));

		verify(questionDao, times(1)).save(question);
	}

	@Test
	void testUpdateQuestion() throws QuestionException {

		QuestionDTO questionDto = mock(QuestionDTO.class);
		Question question = mock(Question.class);
		when(questionDao.save(question)).thenReturn(question);
		when(modelMapper.convertToQuestion(any())).thenReturn(question);
		when(modelMapper.convertToQuestionDTO(any())).thenReturn(questionDto);

		QuestionDTO result = questionService.update(questionDto);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(questionDto.getQuestionId(), result.getQuestionId());
		verify(questionDao, times(1)).save(question);
	}

	@Test
	void testRemoveQuestion() throws QuestionException {
		doNothing().when(questionDao).deleteById(any());

		questionService.remove(0);

		verify(questionDao).deleteById(0);
	}

	@Test
	void testGetQuestion() throws QuestionException {
		Question question = mock(Question.class);
		QuestionDTO questionDto = mock(QuestionDTO.class);

		when(questionDao.findById(question.getQuestionId())).thenReturn(Optional.of(question));
		when(modelMapper.convertToQuestionDTO(any())).thenReturn(questionDto);

		questionService.get(0);

		verify(questionDao).findById(0);
		verify(modelMapper).convertToQuestionDTO(question);
	}

	@Test
	void testGetQuestionForException() {
		when(questionDao.findById(0)).thenReturn(Optional.empty());
		Assertions.assertThrowsExactly(QuestionException.class, () -> questionService.get(0));
	}

	@Test
	void testGetByPage() {
		QuestionDTO questionDto = mock(QuestionDTO.class);
		Question question = mock(Question.class);
		Page<Question> page = new PageImpl<Question>(List.of(question));
		when(modelMapper.convertToQuestionDTOs(any())).thenReturn(List.of(questionDto));

		List<QuestionDTO> result = questionService.getByPage(1, 1);

		assertEquals(page.getSize(), result.size());
	}
}
