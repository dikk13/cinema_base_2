package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.QuestionDao;
import com.kata.cinema.base.dto.AnswerRequestDto;
import com.kata.cinema.base.dto.QuestionRequestDto;
import com.kata.cinema.base.dto.ResultRequestDto;
import com.kata.cinema.base.mappers.AnswerMapper;
import com.kata.cinema.base.mappers.ResultMapper;
import com.kata.cinema.base.models.Answer;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.models.Result;
import com.kata.cinema.base.service.abstracts.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl extends AbstractServiceImpl <Long, Question> implements QuestionService {

    private final QuestionDao questionDao;
    private final ResultMapper resultMapper;
    private final AnswerMapper answerMapper;

    public QuestionServiceImpl(QuestionDao questionDao, ResultMapper resultMapper, AnswerMapper answerMapper) {
        super(questionDao);
        this.questionDao = questionDao;
        this.resultMapper = resultMapper;
        this.answerMapper = answerMapper;
    }
    @Override
    public List<Answer> toAnswer(QuestionRequestDto questionRequestDto){
        List<Answer> answerList = new ArrayList<>();
        for (AnswerRequestDto answerRequestDto: questionRequestDto.getAnswers()){
            Answer answer = answerMapper.toAnswer(answerRequestDto);
            answerList.add(answer);
        }
        return answerList;

    }
    @Override
    public List<Result> toResult(QuestionRequestDto questionRequestDto){
        List<Result> resultList = new ArrayList<>();
        for (ResultRequestDto resultRequestDto: questionRequestDto.getResults()){
            Result result = resultMapper.toResult(resultRequestDto);
            resultList.add(result);
        }
        return resultList;
    }
    @Override
    public void deleteQuestionWithAnswersAndResults(Long questionId){
         questionDao.deleteQuestionWithAnswersAndResultsById(questionId);

    }
}
