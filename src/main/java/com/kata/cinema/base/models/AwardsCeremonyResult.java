package com.kata.cinema.base.models;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table
@Entity
@Data
public class AwardsCeremonyResult {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String nominationStatus;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "awards_ceremony_id")
//    private Set<AwardsCeremony> ceremonySet = new HashSet<>();
//
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "nomination_id")
//    private Set<Nomination> nominationSet = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "person_id")
//    private Set<Nomination> nominationSet = new HashSet<>();
//
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_id")
//    private Set<Nomination> nominationSet = new HashSet<>();


    public AwardsCeremonyResult() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardsCeremonyResult awardsCeremonyResult = (AwardsCeremonyResult) o;

        return (id == awardsCeremonyResult.id) && Objects.equals(nominationStatus, awardsCeremonyResult.nominationStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nominationStatus);
    }

    @Override
    public String toString() {
        return "AwardsCeremonyResult{" +
                "id=" + id +
                ", nominationStatus='" + nominationStatus + '\'' +
                '}';
    }
}
