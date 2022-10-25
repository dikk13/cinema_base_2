package com.kata.cinema.base.config.init;

import com.kata.cinema.base.service.abstracts.CollectionService;
import com.kata.cinema.base.service.abstracts.GenreService;
import com.kata.cinema.base.service.abstracts.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TestDataInitializerConfig {

    @Bean(initMethod = "init")
    @ConditionalOnExpression("${app.initializer.runInitialize}==true && '${spring.jpa.hibernate.ddl-auto}' matches 'create.*'")
    @Autowired
    public TestDataInitializer initTestData(GenreService genreService, MovieService movieService, CollectionService collectionService, PasswordEncoder encoder) {
        return new TestDataInitializer(genreService, movieService, collectionService, encoder);
    }

}
