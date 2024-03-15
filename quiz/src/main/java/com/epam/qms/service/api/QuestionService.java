package com.epam.qms.service.api;

import java.util.List;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.exception.QuestionException;

public interface QuestionService {

	QuestionDTO create(QuestionDTO dataToAdd);

	QuestionDTO update(QuestionDTO dataToModify);

	void remove(int id);

	QuestionDTO get(int id) throws QuestionException;
	
	List<QuestionDTO> getByPage(int pageNo, int pageSize);

}
