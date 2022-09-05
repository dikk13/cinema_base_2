package com.kata.cinema.base.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AwardsCeremonyResult {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_awardsCeremonyResult_id")
    @SequenceGenerator(name = "seq_awardsCeremonyResult_id", sequenceName = "SEQ_AWARDSCEREMONYRESULT_ID", allocationSize = 1)
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


    public AwardsCeremonyResult(Long id, String nominationStatus) {
        this.id = id;
        this.nominationStatus = nominationStatus;
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
