package com.kata.cinema.base.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/uploads/**")
public class ResourcesController {

    @GetMapping("")
    public ResponseEntity<byte[]> getResource(HttpServletRequest request) throws IOException {
        File resource = new File(request.getRequestURI().substring(1));
        return resource.exists() ? new ResponseEntity<>(Files.readAllBytes(resource.toPath()), HttpStatus.OK) :
                new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
    }
}
