package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.dao.entity.ContentDao;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/")

public class AdminContentRestController {

    private final ContentDao contentDao;

    public AdminContentRestController(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    @DeleteMapping("content/{id}")
    public void deleteContent(@PathVariable("id") long id) {
        contentDao.deleteById(id);
    }
}