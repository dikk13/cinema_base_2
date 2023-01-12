package com.kata.cinema.base.webapp.controllers_v2.QuestionPoolRestController;

import com.kata.cinema.base.security.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/PublicistQuestionPoolRestController/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/PublicistQuestionPoolRestController/clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class DeleteQuestionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void deleteQuestionTest() throws Exception {
        this.mockMvc.perform(delete("/api/publicist/news/{id}/questions", "100")
                    .header("Authorization", "Bearer " + jwtUtil.generateToken("email25@mail.com")))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

