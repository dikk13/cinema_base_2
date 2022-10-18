package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
public class Result {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_result_id")
    @SequenceGenerator(name = "seq_result_id", sequenceName = "SEQ_RESULT_ID", allocationSize = 1)
    Long id;

    @Column(name = "count_right_answer")
    Integer countRightAnswer;

    @Column(name = "result")
    String result;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    Question question ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result1 = (Result) o;
        return Objects.equals(id, result1.id) && Objects.equals(countRightAnswer, result1.countRightAnswer) && Objects.equals(result, result1.result) && Objects.equals(question, result1.question);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", countRightAnswer=" + countRightAnswer +
                ", result='" + result + '\'' +
                ", question=" + question +
                '}';
    }
}
