package com.kata.cinema.base.models;


import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table
@Entity
@Data
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "nomination_id")
    private List<AwardsCeremonyResult> awardsCeremonyResult;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nomination nomination = (Nomination) o;

        return (id == nomination.id) && Objects.equals(name, nomination.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
