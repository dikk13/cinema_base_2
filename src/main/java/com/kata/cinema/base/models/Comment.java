package com.kata.cinema.base.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "Comments")
@Entity
@Setter
@ToString
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comments_id")
    @SequenceGenerator(name = "seq_comments_id", sequenceName = "SEQ_COMMENTS_ID", allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "isModerate")
    Boolean isModerate = false;

    @Column(name = "date")
    private LocalDateTime date;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_id", nullable = false, updatable = false)
    private News news;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "level")
    private Integer level;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment comments = (Comment) obj;
        return Objects.equals(id, comments.id) && Objects.equals(text, comments.text) && Objects.equals(date, comments.date);
    }
}
