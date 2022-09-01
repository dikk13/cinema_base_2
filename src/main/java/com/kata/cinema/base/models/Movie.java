package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "countries")
    private String countries;

    @Column(name = "date_release")
    private String date_release;

    @Column(name = "rars")
    private String rars;

    @Column(name = "mpaa")
    private String mpaa;

    @Column(name = "time")
    private String time;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(name, movie.name) && Objects.equals(countries, movie.countries) && Objects.equals(date_release, movie.date_release) && Objects.equals(rars, movie.rars) && Objects.equals(mpaa, movie.mpaa) && Objects.equals(time, movie.time) && Objects.equals(description, movie.description) && Objects.equals(type, movie.type) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countries, date_release, rars, mpaa, time, description, type);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countries='" + countries + '\'' +
                ", date_release='" + date_release + '\'' +
                ", rars='" + rars + '\'' +
                ", mpaa='" + mpaa + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
