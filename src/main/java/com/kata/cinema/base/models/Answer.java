package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_answer_id")
    @SequenceGenerator(name = "seq_answer_id", sequenceName = "SEQ_ANSWER_ID", allocationSize = 1)
    private Long id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "is_right")
    private Boolean isRight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private Question question ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(id, answer1.id) && Objects.equals(answer, answer1.answer) && Objects.equals(isRight, answer1.isRight) && Objects.equals(question, answer1.question);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
