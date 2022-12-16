package com.kata.cinema.base.models;


import com.kata.cinema.base.models.enums.NominationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AwardCeremonyResult {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_awardCeremonyResult_id")
    @SequenceGenerator(name = "seq_awardCeremonyResult_id", sequenceName = "SEQ_AWARDCEREMONYRESULT_ID", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person", insertable = false, updatable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "award_ceremony_id", insertable = false, updatable = false)
    private AwardCeremony ceremony;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomination_id", insertable = false, updatable = false)
    private Nomination nomination;

    @Column
    private NominationStatus nominationStatus;

    public AwardCeremonyResult(Long id, NominationStatus nominationStatus) {
        this.id = id;
        this.nominationStatus = nominationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardCeremonyResult awardCeremonyResult = (AwardCeremonyResult) o;
        return Objects.equals(id, awardCeremonyResult.id) && Objects.equals(nominationStatus, awardCeremonyResult.nominationStatus);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AwardCeremonyResult{" +
                "id=" + id +
                ", nominationStatus='" + nominationStatus + '\'' +
                '}';
    }
}
