package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudioPerformance {

    @ToString.Include
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_studio_performance_id")
    @SequenceGenerator(name = "seq_studio_performance_id", sequenceName = "SEQ_STUDIO_PERFORMANCE_ID",
            allocationSize = 1)
    private Long id;

    @Column
    private String name;
}
