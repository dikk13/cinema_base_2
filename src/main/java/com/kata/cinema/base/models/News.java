package com.kata.cinema.base.models;


import com.kata.cinema.base.models.enums.Rubric;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "news")
@NoArgsConstructor
public class News {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_news_id")
    @SequenceGenerator(name = "seq_news_id", sequenceName = "SEQ_NEWS_ID", allocationSize = 1)
    private Long id;

    @Column(name = "rubric")
    @Enumerated(EnumType.STRING)
    private Rubric rubric;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "title")
    private String title;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "html_body")
    private String htmlBody;

    @OneToMany(mappedBy = "news")
    private List<Question> questions;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = true, updatable = false)
    protected User user;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "news_movie",
            joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<Movie> movies = new java.util.ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(date, news.date) && Objects.equals(title, news.title) &&
                Objects.equals(htmlBody, news.htmlBody);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
