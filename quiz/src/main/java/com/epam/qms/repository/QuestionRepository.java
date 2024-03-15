package com.epam.qms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.epam.qms.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>, PagingAndSortingRepository<Question, Integer> {

}
