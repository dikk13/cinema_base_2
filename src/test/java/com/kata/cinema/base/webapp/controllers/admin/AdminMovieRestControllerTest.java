package com.kata.cinema.base.webapp.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import com.kata.cinema.base.security.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

public class AdminMovieRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Test
    @DatabaseSetup ("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void availableOnlineMovieRequestDto() throws Exception {
        AvailableOnlineMovieRequestDto availableOnlineMovieRequestDto = new AvailableOnlineMovieRequestDto(200, 300, true);
        this.mockMvc.perform(post("/api/admin/movies/100/online")
                        .content(objectMapper.writeValueAsString(availableOnlineMovieRequestDto))
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());}
    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void deactivate () throws Exception {
        AvailableOnlineMovie availableOnlineMovie=new AvailableOnlineMovie(false);

        this.mockMvc.perform(patch("/api/admin/movies/101/online/deactivate")
                .content(objectMapper.writeValueAsString(availableOnlineMovie))
                .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void activate () throws Exception {
        AvailableOnlineMovie availableOnlineMovie=new AvailableOnlineMovie(Boolean.TRUE);
        this.mockMvc.perform(patch("/api/admin/movies/100/online/activate")
                        .content(objectMapper.writeValueAsString(availableOnlineMovie))
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
