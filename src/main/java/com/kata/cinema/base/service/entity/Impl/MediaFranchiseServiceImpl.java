package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.MediaFranchiseDao;
import com.kata.cinema.base.models.MediaFranchise;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.MediaFranchiseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MediaFranchiseServiceImpl extends AbstractServiceImpl<Long, MediaFranchise> implements MediaFranchiseService {

    private final MediaFranchiseDao mediaFranchiseDao;

    protected MediaFranchiseServiceImpl(MediaFranchiseDao mediaFranchiseDao, MediaFranchiseDao mediaFranchiseDao1) {
        super(mediaFranchiseDao);
        this.mediaFranchiseDao = mediaFranchiseDao1;
    }

    @Override
    @Transactional
    public Boolean existByName(String name) {
        return mediaFranchiseDao.existByName(name);
    }
}