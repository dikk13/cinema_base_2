package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
@NoArgsConstructor
public class Person {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;

    @Column(name="first_name", nullable = false, length = 50)
    @Getter
    @Setter
    protected String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = true, length = 50)
    protected String lastName;

    @Column(name = "height", nullable = true, length = 5)
    @Getter
    @Setter
    protected Double height;

    @Column(name = "date_birth", nullable = true, length = 20)
    @Getter
    @Setter
    protected LocalDate dateOfBirth;

    @Getter
    @Setter
    @Column(name = "place_of_birth", nullable = true, length = 50)
    protected String placeOfBirth;

    public Person(String firstName, String lastName, Double height, LocalDate dateOfBirth, String placeOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height=" + height +
                ", dateOfBirth=" + dateOfBirth +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                '}';
    }
}
