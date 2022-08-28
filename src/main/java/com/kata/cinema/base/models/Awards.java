package com.kata.cinema.base.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table
@Entity
@Data
public class Awards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_id", nullable = false)
    private List<AwardsCeremony> awardsCeremony;

    public Awards() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Awards awards = (Awards) o;

       return (id == awards.id) && Objects.equals(name, awards.name) && Objects.equals(description, awards.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Awards{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                '}';
    }
}
