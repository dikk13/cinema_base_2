package com.kata.cinema.base.webapp.controllers_v2.AdminUsersRestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.security.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/AdminUsersRestController/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/AdminUsersRestController/clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AdminUsersRestControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ObjectMapper objectMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void getUserTest() throws Exception {
        mockMvc.perform(get("/api/admin/users/{id}", "101")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.email").value("email101@mail.ru"))
                .andExpect(jsonPath("$.firstName").value("Имя101"))
                .andExpect(jsonPath("$.lastName").value("Фамилия101"))
                .andExpect(jsonPath("$.birthday").value("12.01.2023"));
    }

    @Test
    void updateUserTest() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto(
                "test@mail.ru",
                "ИмяТест",
                "Фамилия101",
                LocalDate.of(2023, 1, 1));

        mockMvc.perform(put("/api/admin/users/{id}", "101")
                        .content(objectMapper.writeValueAsString(userRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/admin/users/{id}", "101")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.email").value("test@mail.ru"))
                .andExpect(jsonPath("$.firstName").value("ИмяТест"))
                .andExpect(jsonPath("$.lastName").value("Фамилия101"))
                .andExpect(jsonPath("$.birthday").value("01.01.2023"));

    }

    @Test
    void createUserTest() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto(
                "new@mail.ru",
                "ИмяNew",
                "ФамилияNew",
                LocalDate.of(2023, 1, 12));

        mockMvc.perform(post("/api/admin/users")
                        .content(objectMapper.writeValueAsString(userRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/admin/users/{id}", "1")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.email").value("new@mail.ru"))
                .andExpect(jsonPath("$.firstName").value("ИмяNew"))
                .andExpect(jsonPath("$.lastName").value("ФамилияNew"))
                .andExpect(jsonPath("$.birthday").value("12.01.2023"));

    }

    @Test
    void disableUserTest() throws Exception {
        mockMvc.perform(delete("/api/admin/users/{id}", "101")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk());
        if (entityManager.createQuery("select u.enabled from User u where u.id = 101", Boolean.class)
                .getSingleResult()) {
            throw new Exception("User is not disabled");
        }
    }

    @Test
    void addRoleTest() throws Exception {
        mockMvc.perform(patch("/api/admin/users/{id}?roleId=3", "101")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk());
        List<Role> roleList = entityManager.createQuery("select  u.role from User u where u.id = 101")
                .getResultList();
        if(!roleList.get(0).getRole().equals("USER") | !roleList.get(1).getRole().equals("PUBLICIST")) {
            throw new Exception("User 101 doesn't have roles USER or PUBLICIST");
        }
    }

    @Test
    void deleteRoleTest() throws Exception {
        mockMvc.perform(delete("/api/admin/users/{id}?roleId=3", "102")
                        .header("Authorization", "Bearer " + jwtUtil.generateToken("email100@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk());
        List<Role> roleList = entityManager.createQuery("select  u.role from User u where u.id = 102")
                .getResultList();
        if(!roleList.get(0).getRole().equals("USER") | (roleList.size() != 1)) {
            throw new Exception("User 101 doesn't have role USER or has more roles");
        }
    }
}
