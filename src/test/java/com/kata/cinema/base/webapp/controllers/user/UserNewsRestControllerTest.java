package com.kata.cinema.base.webapp.controllers.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import com.kata.cinema.base.dto.request.CommentsRequestDto;
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

import java.time.LocalDateTime;

import static com.kata.cinema.base.webapp.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
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
class UserNewsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static String accessToken;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DatabaseSetup("/dataset.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void addComments() throws Exception {
        accessToken = obtainNewAccessToken("email@mail.com", "password", mockMvc);
        CommentsRequestDto requestDto = new CommentsRequestDto(
                "added test text",
                LocalDateTime.parse("2022-06-16T18:37:11")
        );
        this.mockMvc.perform(post("/api/user/news/100/comments?userId=100")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}