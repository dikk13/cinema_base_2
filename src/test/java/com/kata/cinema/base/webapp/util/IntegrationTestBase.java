package com.kata.cinema.base.webapp.util;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public abstract class IntegrationTestBase {

    static final String DEFAULT_USER = "postgres";
    static final String DEFAULT_PASSWORD = "root";
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:15.0")
                    .withUsername(DEFAULT_USER)
                    .withPassword(DEFAULT_PASSWORD);

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

}
