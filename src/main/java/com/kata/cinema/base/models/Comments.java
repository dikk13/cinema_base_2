package com.kata.cinema.base.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comments_id")
    @SequenceGenerator(name = "seq_comments_id", sequenceName = "SEQ_COMMENTS_ID", allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_id", updatable = false)
    private News news;

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, user, news);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comments comments = (Comments) obj;
        return id == comments.id && Objects.equals(text, comments.text) && Objects.equals(date, comments.date) &&
                Objects.equals(user, comments.user) && Objects.equals(news, comments.news);
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", user='" + user + '\'' +
                ", news='" + news +
                '}';
    }
}
