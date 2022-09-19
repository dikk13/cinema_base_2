package com.kata.cinema.base.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "persons_marriage")
@NoArgsConstructor
//TODO без наследования
public class PersonMarriage {

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "person_id")
        protected Long personId;

        @Column(name = "human_id")
        protected Long humanId;
    }
    @EmbeddedId
    private Id id = new Id();

    @Column(name = "marriage_status")
    private String marriageStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_id", insertable = false, updatable = false)
    protected Person human;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonMarriage that = (PersonMarriage) o;
        return Objects.equals(person, that.person) && Objects.equals(marriageStatus, that.marriageStatus) && Objects.equals(human, that.human);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person, marriageStatus, human);
    }

    @Override
    public String toString() {
        return "PersonMarriage{" +
                "person=" + person +
                ", marriageStatus='" + marriageStatus + '\'' +
                ", human=" + human +
                '}';
    }
}
