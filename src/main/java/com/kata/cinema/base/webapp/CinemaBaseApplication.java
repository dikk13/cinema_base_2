package com.kata.cinema.base.webapp;

import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.models.User;
import   org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"com.kata.cinema.base"})
@EntityScan("com.kata.cinema.base.models")
@EnableTransactionManagement
@EnableJpaRepositories({"com.kata.cinema.base"})
public class CinemaBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemaBaseApplication.class, args);
    }
}
