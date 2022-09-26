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

@Entity
@Getter
@Setter
@Table(name = "professions")
@NoArgsConstructor
public class Profession {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_profession_id")
    @SequenceGenerator(name = "seq_profession_id",sequenceName = "SEQ_PROFESSION_ID", allocationSize = 1)
    private Long id;

    @Column(name="name", nullable = false, length = 50)
    protected String name;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Profession that = (Profession) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}