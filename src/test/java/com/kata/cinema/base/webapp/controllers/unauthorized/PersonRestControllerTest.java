package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.ExcertionRequestDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
class PersonRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DatabaseSetup("/dataset.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void getPersonExcertions() throws Exception {

        this.mockMvc.perform(get("/api/persons/{id}/excertions/page/{page}?itemsOnPage=1",
                        100, 1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.entities.[0].id").value(100))
                .andExpect(jsonPath("$.entities.[0].description").value("test text"));
    }

    @Test
    @DatabaseSetup("/dataset.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void createPersonExcertion() throws Exception {
        ExcertionRequestDto excertionRequestDto = new ExcertionRequestDto("description for test excertion");
        this.mockMvc.perform(post("/api/persons/{id}/excertions", 100)
                        .content(objectMapper.writeValueAsString(excertionRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}