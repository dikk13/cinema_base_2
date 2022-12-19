package com.kata.cinema.base.dto.request;

import java.util.List;
/**
 * @param answerId правильный ответ может быть один или несколько;
 *                 в requestDto могут находиться не только правильные ответы
 */
public record QuestionAnswerRequestDto(Long questionId, List<Long> answerId) {

}

