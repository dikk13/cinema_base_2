package com.kata.cinema.base.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nomination_id")
    @SequenceGenerator(name = "seq_nomination_id", sequenceName = "SEQ_NOMINATION_ID", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nomination nomination = (Nomination) o;
        return Objects.equals(id, nomination.id) && Objects.equals(name, nomination.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Nomination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
