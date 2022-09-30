package com.kata.cinema.base.webapp.controllers.admin;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Transactional
@DatabaseSetup("/dataset.xml")
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

    @Test
    void createProductionStudio() throws Exception {
        this.mockMvc.perform(
                        post("/api/admin/studios").content("{\"name\": \"asdstudio\"," +
                                " \"description\": \"studio description\"," +
                                " \"dateFoundation\": \"2022-01-02\"}").contentType("application/json")).andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    void updateProductionStudio() throws Exception {
        this.mockMvc.perform(
                        put("/api/admin/studios/1").content("{\"name\": \"changed\"," +
                                " \"description\": \"updated studio description\"," +
                                " \"dateFoundation\": \"2022-01-02\"}").contentType("application/json")).andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    void deleteProductionStudio() throws Exception {
        entityManager.createQuery("delete from ProductionStudioMovie").executeUpdate();
        this.mockMvc.perform(
                        delete("/api/admin/studios/1").contentType("application/json")).andDo(print()).
                andExpect(status().isOk());
    }
}