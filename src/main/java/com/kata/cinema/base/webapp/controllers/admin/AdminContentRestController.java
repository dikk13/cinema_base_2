package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.service.entity.ContentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/")

public class AdminContentRestController {

    private final ContentService contentService;

    public AdminContentRestController(ContentService contentService) {
        this.contentService = contentService;
    }

    @DeleteMapping("content/{id}")
    public void deleteContent(@PathVariable("id") long id) {
        contentService.deleteById(id);
    }
}