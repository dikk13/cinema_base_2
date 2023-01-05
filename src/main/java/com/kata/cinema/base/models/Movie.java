package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.MovieType;
import com.kata.cinema.base.models.enums.RARS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Table(name = "movie")
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movie_id")
    @SequenceGenerator(name = "seq_movie_id", sequenceName = "SEQ_MOVIE_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "date_release")
    private LocalDate dateRelease;

    @Column(name = "rars")
    @Enumerated(EnumType.STRING)
    private RARS rars;

    @Column(name = "mpaa")
    @Enumerated(EnumType.STRING)
    private MPAA mpaa;
    @Column(name = "time")
    private int time;
    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MovieType type;
    @Column(name = "original_name")
    private String originalName;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<Content> contents;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<MoviePerson> moviePerson;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<AwardCeremonyResult> awardCeremonyResults;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<Score> scores;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "country",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> country;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    @ToString.Exclude
    private List<News> news;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "collection_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id"))
    @ToString.Exclude
    private List<Collection> collections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private List<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) &&
                Objects.equals(country, movie.country) && Objects.equals(dateRelease, movie.dateRelease) &&
                Objects.equals(time, movie.time) && Objects.equals(description, movie.description) &&
                Objects.equals(originalName, movie.originalName);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
