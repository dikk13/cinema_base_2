package com.kata.cinema.base.webapp.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.AnswerRequestDto;
import com.kata.cinema.base.dto.request.QuestionRequestDto;
import com.kata.cinema.base.dto.request.ResultRequestDto;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.security.AuthTokenFilter;
import com.kata.cinema.base.security.jwt.JwtUtil;
import com.kata.cinema.base.service.entity.NewsService;
import com.kata.cinema.base.webapp.util.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
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
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void addQuestions() throws Exception{
        List <AnswerRequestDto> answers = new ArrayList<>();
        List <ResultRequestDto> results = new ArrayList<>();
        QuestionRequestDto questionRequestDto = new QuestionRequestDto(1,"question", answers,results);
        List<QuestionRequestDto> questionRequestDtoList = new ArrayList<>();
        questionRequestDtoList.add(questionRequestDto);
        this.mockMvc.perform(post("/api/admin/news/101/questions")
                        .content(objectMapper.writeValueAsString(questionRequestDtoList))
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void deleteQuestion() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/admin/news/101/questions/{id}", "100")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
