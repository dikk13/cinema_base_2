package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "professions")
@NoArgsConstructor
public class Profession {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;

    @Column(name="name", nullable = false, length = 50)
    @Getter @Setter
    protected String name;

    public Profession(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}