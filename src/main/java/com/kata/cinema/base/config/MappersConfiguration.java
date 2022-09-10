package com.kata.cinema.base.config;

import com.kata.cinema.base.mappers.FolderMovieResponsDtoMapper;
import com.kata.cinema.base.mappers.FolderMovieResponsDtoMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MappersConfiguration {
    @Bean
    FolderMovieResponsDtoMapper folderMovieResponsDtoMapper() {
        return new FolderMovieResponsDtoMapperImpl();
    }
}
