package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_question_id")
    @SequenceGenerator(name = "seq_question_id", sequenceName = "SEQ_QUESTION_ID", allocationSize = 1)
    private Long id;

    private Integer position;

    private String question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_id")
    private News news;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "question")
    private List<Result> results;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(position, question1.position) && Objects.equals(question, question1.question) && Objects.equals(news, question1.news);
    }

    @Override
    public int hashCode()  {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", position=" + position +
                ", question='" + question + '\'' +
                ", news=" + news +
                '}';
    }
}
