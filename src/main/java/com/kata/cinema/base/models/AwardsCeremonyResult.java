package com.kata.cinema.base.models;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table
@Entity
public class AwardsCeremonyResult {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nominationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_ceremony_id")
    private AwardsCeremony ceremony;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomination_id")
    private Nomination nomination;




    public AwardsCeremonyResult() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNominationStatus() {
        return nominationStatus;
    }

    public void setNominationStatus(String nominationStatus) {
        this.nominationStatus = nominationStatus;
    }

    public AwardsCeremony getCeremony() {
        return ceremony;
    }

    public void setCeremony(AwardsCeremony ceremony) {
        this.ceremony = ceremony;
    }

    public Nomination getNomination() {
        return nomination;
    }

    public void setNomination(Nomination nomination) {
        this.nomination = nomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardsCeremonyResult awardsCeremonyResult = (AwardsCeremonyResult) o;

        return (Objects.equals(id, awardsCeremonyResult.id)) && Objects.equals(nominationStatus, awardsCeremonyResult.nominationStatus);
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
