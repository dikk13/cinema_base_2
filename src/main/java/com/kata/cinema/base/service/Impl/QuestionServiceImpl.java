package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.QuestionDao;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.service.abstracts.QuestionService;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl <Long, Question> implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        super(questionDao);
        this.questionDao = questionDao;
    }

    @Override
    @Transactional
    public void deleteQuestionWithAnswersAndResults(Long questionId){
         questionDao.deleteQuestionWithAnswersAndResultsById(questionId);

    }
    @Override
    @Transactional
    public boolean questionBelongToNews(Long newsId, Long questionId) {
        Optional<Question> question = questionDao.getById(questionId);
        Optional<Question> news = questionDao.getById(newsId);
        if (question.isPresent() && news.isPresent()) {
            System.out.println(question.get().getNews().getId());
            System.out.println(newsId);
            return question.get().getNews().getId().equals(newsId);
        } else {
            throw new NullPointerException();
        }


    }
}
