package com.epam.qms.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.epam.qms.dto.QuestionDTO;
import com.epam.qms.exception.QuestionException;
import com.epam.qms.service.api.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class QuestionControllerTest {

	@MockBean
	private QuestionService questionService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private QuestionDTO questionDto;

	@BeforeEach
	void setup() {
		questionDto = QuestionDTO.builder().description("Are you fine?").options(List.of("Yes", "No")).answer("Yes")
				.topic("Self").difficulty("Easy").marks(2).build();
	}

	@Test
	void validGetQuestion() throws Exception {
		when(questionService.get(1)).thenReturn(questionDto);

		mockMvc.perform(get("/api.qms/questions/1").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				jsonPath("$.questionId").value(0));
	}

	@Test
	void inValidGetQuestion() throws Exception {
		when(questionService.get(anyInt())).thenThrow(new QuestionException("Question Id not Found"));

		mockMvc.perform(get("/api.qms/questions/10").accept(MediaType.APPLICATION_JSON))
				.andExpectAll(status().isBadRequest(), jsonPath("$.message").value("Question Id not Found"));
	}

	@Test
	void validCreateQuestion() throws Exception {
		when(questionService.create(any())).thenReturn(questionDto);

		mockMvc.perform(post("/api.qms/questions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(questionDto))).andExpectAll(status().isCreated(),
						jsonPath("$.questionId").value(0), jsonPath("$.description").value("Are you fine?"),
						jsonPath("$.answer").value("Yes"), jsonPath("$.difficulty").value("Easy"));
	}

	@Test
	void inValidCreateQuestion() throws Exception {
		questionDto = QuestionDTO.builder().options(List.of("Yes", "No")).answer("Yes").topic("Self").difficulty("Easy")
				.marks(2).build();
		when(questionService.create(any())).thenReturn(questionDto);

		mockMvc.perform(post("/api.qms/questions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(questionDto))).andExpectAll(status().isBadRequest(),
						jsonPath("$.message").value("[Question Description is required field]"));
	}

	@Test
	void validUpdateQuestion() throws Exception {
		when(questionService.update(any())).thenReturn(questionDto);

		mockMvc.perform(put("/api.qms/questions/1").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(questionDto))).andExpectAll(status().isOk(),
						jsonPath("$.description").value("Are you fine?"), jsonPath("$.marks").value(2));
	}

	@Test
	void inValidUpdateQuestion() throws Exception {
		when(questionService.update(any())).thenThrow(new NullPointerException());

		mockMvc.perform(put("/api.qms/questions/1").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(questionDto))).andExpectAll(status().isInternalServerError());
	}

	@Test
	void validRemoveQuestion() throws Exception {
		doNothing().when(questionService).remove(anyInt());
		mockMvc.perform(delete("/api.qms/questions/1")).andExpect(status().isNoContent());
	}

	@Test
	void validGetQuestions() throws Exception {
		when(questionService.getByPage(1, 1)).thenReturn(List.of(questionDto));
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
		queryParameters.add("pageNo", "1");
		queryParameters.add("pageSize", "2");
		mockMvc.perform(get("/api.qms/questions").accept(MediaType.APPLICATION_JSON).queryParams(queryParameters))
				.andExpect(status().isOk());
	}

}
