package com.kata.cinema.base.webapp.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.AnswerRequestDto;
import com.kata.cinema.base.dto.QuestionRequestDto;
import com.kata.cinema.base.dto.ResultRequestDto;
import com.kata.cinema.base.models.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@DatabaseSetup("/dataset_for_question_contr.xml")
@DatabaseTearDown("/empty_dataset.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class AdminQuestionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addQuestions() throws Exception{
        List <AnswerRequestDto> answers = new ArrayList<>();
        List <ResultRequestDto> results = new ArrayList<>();
        QuestionRequestDto questionRequestDto = new QuestionRequestDto(1,"question", answers,results);
        List<QuestionRequestDto> questionRequestDtoList = new ArrayList<>();
        questionRequestDtoList.add(questionRequestDto);
        this.mockMvc.perform(post("/api/admin/news/101/questions")
                        .content(objectMapper.writeValueAsString(questionRequestDtoList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void deleteQuestion() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/admin/news/101/questions/{id}", "100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
