package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "movie_person")
@NoArgsConstructor
//TODO без наследования
public class MoviePerson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movie_person_id")
    @SequenceGenerator(name = "seq_movie_person_id", sequenceName = "SEQ_MOVIE_PERSON_ID", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "movie_id", insertable = false, updatable = false)
    protected Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "profession_id", insertable = false, updatable = false)
    protected Profession profession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "person_id", insertable = false, updatable = false)
    protected Person person;

    @Column (name = "type_character", nullable = false, length = 20)
    protected String typeCharacter;

    @Column (name = "name_role", length = 100)
    protected String nameRole;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MoviePerson that = (MoviePerson) o;
        return Objects.equals(id, that.id) &&Objects.equals(typeCharacter, that.typeCharacter) &&
                Objects.equals(nameRole, that.nameRole);
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
