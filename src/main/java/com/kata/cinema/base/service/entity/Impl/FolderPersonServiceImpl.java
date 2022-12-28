package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.FolderPersonDao;
import com.kata.cinema.base.models.FolderPerson;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.FolderPersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FolderPersonServiceImpl extends AbstractServiceImpl<Long, FolderPerson> implements FolderPersonService {

    private final FolderPersonDao folderPersonDao;

    protected FolderPersonServiceImpl(AbstractDao<Long, FolderPerson> abstractDao, FolderPersonDao folderPersonDao) {
        super(abstractDao);
        this.folderPersonDao = folderPersonDao;
    }

    @Override
    public List<FolderPerson> getFolderPersonById(Long folderPersonId) {
        return folderPersonDao.getFolderPersonById(folderPersonId);
    }

}
