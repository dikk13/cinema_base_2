package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "persons")
@NoArgsConstructor
public class Person {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person_id")
    @SequenceGenerator(name = "seq_person_id", sequenceName = "SEQ_PERSON_ID", allocationSize = 1)
    private Long id;

    @Column(name="first_name", nullable = false, length = 50)
    protected String firstName;

    @Column(name = "last_name", nullable = true, length = 50)
    protected String lastName;

    @Column(name = "height", nullable = true, length = 5)
    protected Double height;

    @Column(name = "date_birth", nullable = true, length = 20)
    protected LocalDate dateOfBirth;

    @Column(name = "place_of_birth", nullable = true, length = 50)
    protected String placeOfBirth;

    @OneToMany(mappedBy = "person")
    private List<PersonMarriage> personMarriagesPerson;

    @OneToMany(mappedBy = "human")
    private List<PersonMarriage> personMarriagesHuman;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(height, person.height) && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(placeOfBirth, person.placeOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, height, dateOfBirth, placeOfBirth);
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
