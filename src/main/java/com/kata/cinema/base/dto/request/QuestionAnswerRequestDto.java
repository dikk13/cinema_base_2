package com.kata.cinema.base.dto.request;

import java.util.List;
/**
 * @param answerId правильный ответ может быть один или несколько
 */
public record QuestionAnswerRequestDto(Long questionId, List<Long> answerId) {

}

