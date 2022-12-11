package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.FolderPerson;

import java.util.List;
import java.util.Optional;

public interface FolderPersonService extends AbstractService<Long, FolderPerson> {
    public List<FolderPerson> getFolderPersonById (Long folderPersonId);


}
