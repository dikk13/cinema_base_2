package com.kata.cinema.base.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "persons_marriage")
@NoArgsConstructor
public class PersonMarriage {
    @Embeddable
    @NoArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "person_id")
        protected Long personId;

        @Column(name = "human_id")
        protected Long humanId;

        public Id (Long personId, Long humanId) {
            this.personId = personId;
            this.humanId = humanId;
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @Column(name = "marriageStatus")
    protected String marriageStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    protected Person person;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_id", insertable = false, updatable = false)
    protected Person human;

    public PersonMarriage(Person person, Person human, String marriageStatus) {
        this.id.personId = person.getId();
        this.id.humanId = human.getId();
        this.marriageStatus = marriageStatus;
    }
}
