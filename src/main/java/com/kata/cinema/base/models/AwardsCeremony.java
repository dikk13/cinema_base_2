package com.kata.cinema.base.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Table
@Entity
public class AwardsCeremony {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String dateEvent;

    @Column
    private String placeEvent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_id")
    private Awards awards;


    public AwardsCeremony() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getPlaceEvent() {
        return placeEvent;
    }

    public void setPlaceEvent(String placeEvent) {
        this.placeEvent = placeEvent;
    }

    public Awards getAwards() {
        return awards;
    }

    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardsCeremony awardsCeremony = (AwardsCeremony) o;

        return (Objects.equals(id, awardsCeremony.id)) && Objects.equals(dateEvent, awardsCeremony.dateEvent)
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
                ", awards=" + awards +
                '}';
    }
}
