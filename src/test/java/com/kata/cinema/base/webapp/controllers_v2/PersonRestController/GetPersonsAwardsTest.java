package com.kata.cinema.base.webapp.controllers_v2.PersonRestController;

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

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/PersonRestController/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/PersonRestController/clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetPersonsAwardsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMoviesAwardsTest() throws Exception {
        this.mockMvc.perform(get("/api/persons/{id}/awards", "100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].['name']").value("Персона 100"))
                .andExpect(jsonPath("$[0].['nominationStatus']").value("WINNER"));
    }

}
