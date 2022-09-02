package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Awards {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_awards_id")
    @SequenceGenerator(name="seq_awards_id",sequenceName="SEQ_AWARDS_ID", allocationSize=1)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;


    public Awards(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Awards awards = (Awards) o;

       return (Objects.equals(id, awards.id)) && Objects.equals(name, awards.name) && Objects.equals(description, awards.description);
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
