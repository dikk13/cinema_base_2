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
    private String typeCharacter;

    @Column (name = "name_role", nullable = true, length = 100)
    private String nameRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "profession_id", insertable = false, updatable = false)
    private Profession profession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "person_id", insertable = false, updatable = false)
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MoviePerson that = (MoviePerson) o;
        return Objects.equals(typeCharacter, that.typeCharacter) && Objects.equals(nameRole, that.nameRole);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "MoviePerson{" +
                "movie=" + movie +
                ", profession=" + profession +
                ", person=" + person +
                ", typeCharacter='" + typeCharacter + '\'' +
                ", nameRole='" + nameRole + '\'' +
                '}';
    }
}
