package com.kata.cinema.base.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
@Data
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private String name;


    public Nomination(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nomination nomination = (Nomination) o;

        return (Objects.equals(id, nomination.id)) && Objects.equals(name, nomination.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Nomination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
