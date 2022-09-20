package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.MovieType;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//@NamedEntityGraphs({
//        @NamedEntityGraph(
//                name = "movieResponseDtoGraph",
//                attributeNodes = {@NamedAttributeNode(value = "genres"),
//                        @NamedAttributeNode(value = "moviePerson", subgraph = "moviePersonSub")},
//                subgraphs = { @NamedSubgraph()}
//        )
//})

@NamedEntityGraph(name = "movieResponseDtoGraph",
        attributeNodes = {
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "originalName"),
                @NamedAttributeNode(value = "time"),
                @NamedAttributeNode(value = "dateRelease"),
                @NamedAttributeNode(value = "genres"),
                @NamedAttributeNode(value = "moviePerson", subgraph = "moviePersonSub"),
        },
        subgraphs = {
                @NamedSubgraph(name = "moviePersonSub", attributeNodes = @NamedAttributeNode("person"))
        }
)

@Table (name = "movies")
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
    private RARS rars;

    @Column(name = "mpaa")
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
    private Set<MoviePerson> moviePerson;

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
            name = "collection_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private List<Collection> collections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();

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

    public Integer timeToInt(String time) {
        return Integer.valueOf(time);
    }

}
