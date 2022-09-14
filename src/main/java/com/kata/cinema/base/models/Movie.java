package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movie_id")
    @SequenceGenerator(name = "seq_movie_id", sequenceName = "SEQ_MOVIE_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "countries")
    private String countries;

    @Column(name = "date_release")
    private String dateRelease;

    @Column(name = "rars")
    @Enumerated(EnumType.STRING)
    private RARS rars;

    @Column(name = "mpaa")
    @Enumerated(EnumType.STRING)
    private MPAA mpaa;

    @Column(name = "time")
    private String time;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private MovieType type;

    @Column(name = "original_name")
    private String originalName;

    @OneToMany(mappedBy = "movie")
    private List<Content> contents;

    @OneToMany(mappedBy = "movie")
    private List<MoviePerson> moviePerson;

    @OneToMany(mappedBy = "movie")
    private List<AwardCeremonyResult> awardCeremonyResults;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    private List<News> news;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "folder_movie_to_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "folder_id"))
    private List<FolderMovie> folderMovies ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "collection_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private List<Collection> collections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(countries, movie.countries) && Objects.equals(dateRelease, movie.dateRelease) && Objects.equals(rars, movie.rars) && Objects.equals(mpaa, movie.mpaa) && Objects.equals(time, movie.time) && Objects.equals(description, movie.description) && Objects.equals(type, movie.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countries, dateRelease, rars, mpaa, time, description, type);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countries='" + countries + '\'' +
                ", date_release='" + dateRelease + '\'' +
                ", rars='" + rars + '\'' +
                ", mpaa='" + mpaa + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
