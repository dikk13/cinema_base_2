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
    public void deleteQuestionWithAnswersAndResults(Long newsId, Long questionId){
        Optional<Question> question = questionDao.getById(questionId);
        if (question.orElseThrow().getNews().getId().equals(newsId)) {
            questionDao.deleteQuestionWithAnswersAndResultsById(questionId);
        } else {
            throw new NullPointerException();
        }
    }
}
