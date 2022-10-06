package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
//@Table(name = "movie_person")
@NoArgsConstructor
//TODO без наследования
public class MoviePerson extends Movie {

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
