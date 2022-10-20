package com.kata.cinema.base.config.init;

import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDataInitializerConfig {

    @Bean(initMethod = "init")
    @ConditionalOnExpression("${app.initializer.runInitialize}==true && '${spring.jpa.hibernate.ddl-auto}' matches 'create.*'")
    @Autowired
    public TestDataInitializer initTestData(GenreService genreService, MovieService movieService, CollectionService collectionService) {
        return new TestDataInitializer(genreService, movieService, collectionService);
    }

}
