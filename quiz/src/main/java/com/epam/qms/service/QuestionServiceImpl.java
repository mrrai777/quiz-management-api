package com.epam.qms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.entity.Question;
import com.epam.qms.exception.QuestionException;
import com.epam.qms.repository.QuestionRepository;
import com.epam.qms.service.api.QuestionService;
import com.epam.qms.util.Constants;
import com.epam.qms.util.MappingHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private MappingHelper mapper;

	@Override
	public QuestionDTO create(QuestionDTO questionDto) {
		log.info("Create Question with data : {}", questionDto);
		Question question = questionRepository.save(mapper.convertToQuestion(questionDto));
		return mapper.convertToQuestionDTO(question);
	}

	@Override
	public QuestionDTO update(QuestionDTO questionDto) {
		log.info("Update Question with data : {}", questionDto);
		return mapper.convertToQuestionDTO(questionRepository.save(mapper.convertToQuestion(questionDto)));
	}

	@Override
	public void remove(int questionId) {
		log.info("Remove Question having Id : {}", questionId);
		questionRepository.deleteById(questionId);
	}

	@Override
	public QuestionDTO get(int id) throws QuestionException {
		log.info("Get Question having questionId : {}", id);
		return questionRepository.findById(id).map(mapper::convertToQuestionDTO)
				.orElseThrow(() -> new QuestionException(Constants.INVALID_QUESTION_ID));
	}

	@Override
	public List<QuestionDTO> getByPage(int pageNo, int pageSize) {
		log.info("Get Question of page number {}", pageNo);
		Pageable page = PageRequest.of(pageNo, pageSize);
		Page<Question> questions = questionRepository.findAll(page);
		return mapper.convertToQuestionDTOs((List<Question>)questions);
	}

}
