package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.dto.request.SearchMovieRequestDto;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class SearchHeaderDtoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DatabaseSetup("/dataset.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void getAuthors() throws Exception {
        List<Long> personsId = Arrays.asList(100L, 101L, 103L);
        SearchMovieRequestDto searchMovieRequestDto = new SearchMovieRequestDto(100L, personsId);
        this.mockMvc.perform(post("/api/search/movies/{id}/authors/page/{pageNumber}", 100, 1)
                        .content(objectMapper.writeValueAsString(searchMovieRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.entities.[0].professionId").value(100))
                .andExpect(jsonPath("$.entities.[0].personId").value(100))
                .andExpect(jsonPath("$.entities.[0].fullName").value("Name Last"))
                .andExpect(jsonPath("$.entities.[0].originalFullName").value("OName OLast"))
                .andExpect(jsonPath("$.entities.[0].type").value("NO_CHARACTER_MOVIE"))
                .andExpect(jsonPath("$.entities.[0].nameCharacter").value("role1"))
                .andExpect(jsonPath("$.entities.[0].photoUrl").value("/url_photo_person100"))

                .andExpect(jsonPath("$.entities.[1].professionId").value(100))
                .andExpect(jsonPath("$.entities.[1].personId").value(101))
                .andExpect(jsonPath("$.entities.[1].fullName").value("Name2 Last2"))
                .andExpect(jsonPath("$.entities.[1].originalFullName").value("OName2 OLast2"))
                .andExpect(jsonPath("$.entities.[1].type").value("NO_CHARACTER_MOVIE"))
                .andExpect(jsonPath("$.entities.[1].nameCharacter").value("role2"))
                .andExpect(jsonPath("$.entities.[1].photoUrl").value("/url_photo_person102"));
    }

}
