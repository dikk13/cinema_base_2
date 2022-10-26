package com.kata.cinema.base.webapp.controllers.admin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.ProductionStudioRequestDto;

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
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DatabaseSetup("/empty_dataset.xml")
    @DatabaseTearDown(value = "/empty_dataset.xml")
    void createProductionStudio() throws Exception {
        ProductionStudioRequestDto requestDto = new ProductionStudioRequestDto(
                "asdstudio",
                "studio description",
                LocalDate.parse("2022-01-02")
        );
        this.mockMvc.perform(post("/api/admin/studios")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/empty_dataset.xml")
    @DatabaseTearDown(value = "/empty_dataset.xml")
    void updateProductionStudio() throws Exception {
        ProductionStudioRequestDto requestDto = new ProductionStudioRequestDto(
                "changed",
                "updated studio description",
                LocalDate.parse("2022-01-02")
        );
        this.mockMvc.perform(put("/api/admin/studios/100")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/empty_dataset.xml")
    @DatabaseTearDown(value = "/empty_dataset.xml")
    void deleteProductionStudio() throws Exception {
        entityManager.createQuery("delete from ProductionStudioMovie").executeUpdate();
        this.mockMvc.perform(delete("/api/admin/studios/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }
}