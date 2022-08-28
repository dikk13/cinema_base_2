package com.kata.cinema.base.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Table
@Entity
@Data
public class AwardsCeremony {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String dateEvent;

    @Column
    private String placeEvent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_ceremony_id")
    private List<AwardsCeremonyResult> awardsCeremonyResult;


    public AwardsCeremony() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardsCeremony awardsCeremony = (AwardsCeremony) o;

        return (id == awardsCeremony.id) && Objects.equals(dateEvent, awardsCeremony.dateEvent)
                && Objects.equals(placeEvent, awardsCeremony.placeEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateEvent, placeEvent);

    }

    @Override
    public String toString() {
        return "AwardsCeremony{" +
                "id=" + id +
                ", dateEvent='" + dateEvent + '\'' +
                ", placeEvent='" + placeEvent + '\'' +
                ", awardsCeremonyResult=" + awardsCeremonyResult +
                '}';
    }
}
