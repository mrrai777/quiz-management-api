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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.qms.dto.QuizDTO;
import com.epam.qms.exception.QuizException;
import com.epam.qms.service.api.QuizService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class QuizControllerTest {

	@MockBean
	private QuizService quizService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	@WithMockUser(roles = "USER")
	void validGetQuiz() throws Exception {
		QuizDTO quizDto = QuizDTO.builder().title("Sem1").marks(10).build();
		when(quizService.get(1)).thenReturn(quizDto);

		mockMvc.perform(get("/api.qms/quizzes/1").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				jsonPath("$.quizId").value(0));
	}

	@Test
	@WithMockUser(roles = "USER")
	void inValidGetQuiz() throws Exception {
		when(quizService.get(anyInt())).thenThrow(new QuizException("Quiz Id not Found"));

		mockMvc.perform(get("/api.qms/quizzes/10").accept(MediaType.APPLICATION_JSON))
				.andExpectAll(status().isBadRequest(), jsonPath("$.message").value("Quiz Id not Found"));
	}

	@Test
	void validCreateQuiz() throws Exception {
		QuizDTO quizDto = QuizDTO.builder().title("Sem1").marks(20).build();
		when(quizService.create(any())).thenReturn(quizDto);

		mockMvc.perform(post("/api.qms/quizzes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(quizDto)))
				.andExpectAll(status().isCreated(), jsonPath("$.quizId").value(0));
	}

	@Test
	void inValidCreateQuiz() throws Exception {
		QuizDTO quizDto = QuizDTO.builder().marks(20).build();
		when(quizService.create(any())).thenReturn(quizDto);

		mockMvc.perform(post("/api.qms/quizzes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(quizDto)))
				.andExpectAll(status().isBadRequest(), jsonPath("$.message").value("[Quiz Title is mandatory]"));
	}

	@Test
	void validUpdateQuiz() throws Exception {
		QuizDTO quizDto = QuizDTO.builder().title("Sem1").marks(20).build();
		when(quizService.modify(any())).thenReturn(quizDto);

		mockMvc.perform(put("/api.qms/quizzes/1").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(quizDto)))
				.andExpectAll(status().isOk(), jsonPath("$.title").value("Sem1"), jsonPath("$.marks").value(20));
	}

	@Test
	void inValidUpdateQuiz() throws Exception {
		QuizDTO quizDto = QuizDTO.builder().title("Sem1").marks(20).build();
		when(quizService.modify(any())).thenThrow(new NullPointerException());

		mockMvc.perform(put("/api.qms/quizzes/1").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(quizDto))).andExpectAll(status().isInternalServerError());
	}

	@Test
	void validRemoveQuiz() throws Exception {
		doNothing().when(quizService).remove(anyInt());

		mockMvc.perform(delete("/api.qms/quizzes/1")).andExpect(status().isNoContent());
	}
}
