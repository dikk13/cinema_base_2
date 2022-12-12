package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.FolderPerson;

import java.util.List;
import java.util.Optional;

public interface FolderPersonDao extends AbstractDao<Long, FolderPerson> {
    public List<FolderPerson> getFolderPersonById(Long folderPersonId);

    public List<FolderPerson> getFolderPersonByName(String name);
}
