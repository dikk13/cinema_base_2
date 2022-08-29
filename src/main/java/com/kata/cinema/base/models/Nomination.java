package com.kata.cinema.base.models;


import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table
@Entity
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "nomination_id")
    private List<AwardsCeremonyResult> awardsCeremonyResult;

    public Nomination() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AwardsCeremonyResult> getAwardsCeremonyResult() {
        return awardsCeremonyResult;
    }

    public void setAwardsCeremonyResult(List<AwardsCeremonyResult> awardsCeremonyResult) {
        this.awardsCeremonyResult = awardsCeremonyResult;
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
                ", awardsCeremonyResult=" + awardsCeremonyResult +
                '}';
    }
}
