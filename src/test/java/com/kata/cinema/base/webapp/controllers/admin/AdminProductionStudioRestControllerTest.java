package com.kata.cinema.base.webapp.controllers.admin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.ProductionStudioRequestDto;

import com.kata.cinema.base.security.jwt.JwtUtil;
import com.kata.cinema.base.service.entity.ProductionStudioService;
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

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
class AdminProductionStudioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;


    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown(value = "/empty_dataset.xml")
    void createProductionStudio() throws Exception {
        ProductionStudioRequestDto requestDto = new ProductionStudioRequestDto(
                "asdstudio",
                "studio description",
                LocalDate.parse("2022-01-02")
        );
        this.mockMvc.perform(post("/api/admin/studios")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown(value = "/empty_dataset.xml")
    void updateProductionStudio() throws Exception {
        ProductionStudioRequestDto requestDto = new ProductionStudioRequestDto(
                "changed",
                "updated studio description",
                LocalDate.parse("2022-01-02")
        );
        this.mockMvc.perform(put("/api/admin/studios/100")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown(value = "/empty_dataset.xml")
    void deleteProductionStudio() throws Exception {
        this.mockMvc.perform(delete("/api/admin/studios/100")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }
}