package com.kata.cinema.base.config.init;

import com.kata.cinema.base.service.abstracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class TestDataInitializerConfig {

    @Bean(initMethod = "init")
    @ConditionalOnExpression("${app.initializer.runInitialize}==true && '${spring.jpa.hibernate.ddl-auto}' matches 'create.*'")
    @Autowired
    public TestDataInitializer initTestData(GenreService genreService) {
        return new TestDataInitializer(genreService);
    }

}
