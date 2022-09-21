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
@Getter
@Setter
@NoArgsConstructor
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_award_id")
    @SequenceGenerator(name = "seq_award_id", sequenceName = "SEQ_AWARD_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return (Objects.equals(id, award.id)) && Objects.equals(name, award.name) && Objects.equals(description, award.description);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Award{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                '}';
    }
}
