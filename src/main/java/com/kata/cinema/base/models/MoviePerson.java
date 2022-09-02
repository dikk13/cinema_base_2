package com.kata.cinema.base.models;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "movie_person")
@NoArgsConstructor
public class MoviePerson {
    @Embeddable
    @NoArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "movie_id")
        protected Long movieId;

        @Column(name = "profession_id")
        protected Long professionId;

        @Column(name = "person_id")
        protected Long personId;

        public Id (Long movieId, Long professionId, Long personId) {
            this.movieId = movieId;
            this.professionId = professionId;
            this.personId = personId;
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @Column (name = "type_character", nullable = false, length = 20)
    protected String typeCharacter;

    @Column (name = "name_role", nullable = true, length = 100)
    protected String nameRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "movie_id", insertable = false, updatable = false)
    protected Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "profession_id", insertable = false, updatable = false)
    protected Profession profession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "person_id", insertable = false, updatable = false)
    protected Person person;

    public MoviePerson (Movie movie, Profession profession, Person person, String typeCharacter, String nameRole) {
        this.id.movieId = movie.getId();
        this.id.professionId = profession.getId();
        this.id.personId = person.getId();
        this.typeCharacter = typeCharacter;
        this.nameRole = nameRole;

    }
}
