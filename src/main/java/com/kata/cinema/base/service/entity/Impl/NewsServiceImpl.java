package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.NewsDao;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.RedactorComment;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.NewsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kata.cinema.base.models.enums.RedactorStatus.RESOLVED;

@Service
public class NewsServiceImpl extends AbstractServiceImpl<Long, News> implements NewsService {
    private final NewsDao newsDao;

    public NewsServiceImpl(NewsDao newsDao) {
        super(newsDao);
        this.newsDao = newsDao;
    }

    @Override
    public News getNewsById(Long newsId) {
        Optional<News> news = newsDao.getById(newsId);
        if (news.isPresent()) {
            return news.get();
        } else {
            throw new NullPointerException("Новость не найдена");
        }
    }

    @Override
    public void changeModerateFlag(Long id, RedactorComment redactorComment) {
        if (redactorComment.getStatus() == RESOLVED) {
            News news = getNewsById(id);
            news.setIsModerate(true);
            update(news);
        }
    }

}
