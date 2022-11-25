package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.models.Chapter;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ChapterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ChapterServiceImpl extends AbstractServiceImpl<Long, Chapter> implements ChapterService {

    protected ChapterServiceImpl(AbstractDao<Long, Chapter> abstractDao) {
        super(abstractDao);
    }

    @Override
    @Transactional
    public void updateById(Long id, String name) {
        Optional<Chapter> optionalChapter = getById(id);
        if (optionalChapter.isPresent()) {
            Chapter chapter = optionalChapter.get();
            chapter.setId(id);
            chapter.setName(name);
        }
    }
}
