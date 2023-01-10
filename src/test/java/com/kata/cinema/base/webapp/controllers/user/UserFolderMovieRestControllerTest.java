package com.kata.cinema.base.webapp.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kata.cinema.base.dto.request.FolderRequestDto;
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

import static com.kata.cinema.base.webapp.util.IntegrationTestingAccessTokenUtil.obtainNewAccessToken;
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
public class UserFolderMovieRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private static String accessToken;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DatabaseSetup("/dataset.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void deleteFolderMovieById() throws Exception{
        accessToken=obtainNewAccessToken("email@mail.com", "password", mockMvc);
        this.mockMvc.perform(delete("/api/user/folders/100/movies")
                        .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DatabaseSetup("/dataset.xml")
    @DatabaseTearDown("/empty_dataset.xml")
    void createNewFolderByMovie () throws Exception{
        accessToken=obtainNewAccessToken("email@mail.com", "password", mockMvc);
        FolderRequestDto folderRequestDto=new FolderRequestDto("N","D");
        this.mockMvc.perform(post("/api/user/folders/movies")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(folderRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

