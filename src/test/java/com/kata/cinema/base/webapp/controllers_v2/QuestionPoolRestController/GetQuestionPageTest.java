package com.kata.cinema.base.webapp.controllers_v2.QuestionPoolRestController;

import com.kata.cinema.base.security.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/PublicistQuestionPoolRestController/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/PublicistQuestionPoolRestController/clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetQuestionPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void getQuestionPageTest() throws Exception {
        this.mockMvc.perform(get("/api/publicist/questions/page/{pageNumber}", "1")
                    .param("itemsOnPage", "1")
                    .header("Authorization", "Bearer " + jwtUtil.generateToken("email25@mail.com")))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(jsonPath("$.count").value("1"))
//                .andExpect(jsonPath("$.entities[0].['id']").value("100"));
    }
}
