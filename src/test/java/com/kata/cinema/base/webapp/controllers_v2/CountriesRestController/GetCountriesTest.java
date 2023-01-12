package com.kata.cinema.base.webapp.controllers_v2.CountriesRestController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/CountriesRestController/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/CountriesRestController/clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetCountriesTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCountriesTest() throws Exception {
        this.mockMvc.perform(get("/api/countries"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].['name']").value("USA"))
                .andExpect(jsonPath("$[1].['name']").value("Russia"))
                .andExpect(jsonPath("$[2].['name']").value("France"))
                .andExpect(jsonPath("$[3].['name']").value("Canada"))
                .andExpect(jsonPath("$[4].['name']").value("China"));
    }
    @Test
    void searchCountriesTest() throws Exception {
        mockMvc.perform(get("/api/search/countries?filterPattern=C"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("Canada"))
                .andExpect(jsonPath("$[1].name").value("China"));
    }
}
