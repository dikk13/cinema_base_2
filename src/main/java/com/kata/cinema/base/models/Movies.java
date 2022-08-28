package com.kata.cinema.base.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Data
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    @OneToOne(mappedBy = "movies")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Score scores;

    @OneToOne(mappedBy = "movies")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Content content;

    @ManyToMany(mappedBy = "movies")
    private List<Genres> genres;

    @ManyToMany(mappedBy = "movies")
    private List<Collections> collections;

    public Movies() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return id == movies.id && Objects.equals(name, movies.name) && Objects.equals(countries, movies.countries) && Objects.equals(date_release, movies.date_release) && Objects.equals(rars, movies.rars) && Objects.equals(mpaa, movies.mpaa) && Objects.equals(time, movies.time) && Objects.equals(description, movies.description) && Objects.equals(type, movies.type) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countries, date_release, rars, mpaa, time, description, type);
    }

    @Override
    public String toString() {
        return "Movies{" +
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
