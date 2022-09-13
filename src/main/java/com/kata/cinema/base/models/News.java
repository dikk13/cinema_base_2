package com.kata.cinema.base.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class News {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_news_id")
    @SequenceGenerator(name = "seq_news_id", sequenceName = "SEQ_NEWS_ID", allocationSize = 1)
    private Long id;

    @Column(name = "rubric")
    private String rubric;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "title")
    private String title;

    @Column(name = "html_body")
    private String htmlBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    protected User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_movie",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && Objects.equals(rubric, news.rubric) && Objects.equals(date, news.date) && Objects.equals(title, news.title) && Objects.equals(htmlBody, news.htmlBody) && Objects.equals(user, news.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rubric, date, title, htmlBody, user);
    }
}
