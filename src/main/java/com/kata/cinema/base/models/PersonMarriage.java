package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "persons_marriage")
@NoArgsConstructor
//TODO без наследования
public class PersonMarriage extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @Column(name = "marriageStatus")
    protected String marriageStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_id", insertable = false, updatable = false)
    protected Person human;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonMarriage that = (PersonMarriage) o;
        return Objects.equals(marriageStatus, that.marriageStatus);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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
