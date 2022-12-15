package com.kata.cinema.base.webapp.controllers.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.AddressRequestDto;
import com.kata.cinema.base.security.jwt.JwtUtil;
import com.kata.cinema.base.webapp.util.IntegrationTestBase;
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

public class AdminAddressRestControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void addNewAddress() throws Exception{
        AddressRequestDto addressRequestDto = new AddressRequestDto(
                "street",
                "city"
        );
        this.mockMvc.perform(post("/api/admin/address")
                        .content(objectMapper.writeValueAsString(addressRequestDto))
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void deleteAddressById() throws Exception{
        this.mockMvc.perform(delete("/api/admin/address/100")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("/dataset_for_question_contr.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void updateAddress() throws Exception{
        AddressRequestDto requestDto = new AddressRequestDto(
                "street",
                "city"
        );
        this.mockMvc.perform(put("/api/admin/address/100")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email24@mail.com"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
    }
}
