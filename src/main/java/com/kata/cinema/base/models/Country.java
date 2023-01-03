package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table
@Entity
@Setter
@Getter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_award_id")
    @SequenceGenerator(name = "seq_country", sequenceName = "SEQ_COUNTRY", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}


