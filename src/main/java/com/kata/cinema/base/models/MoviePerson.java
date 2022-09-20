package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "movie_person")
@NoArgsConstructor
//TODO без наследования
public class MoviePerson {

    @Embeddable
    @NoArgsConstructor
    @Getter
    @Setter
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
    private Id id = new Id();

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


}
