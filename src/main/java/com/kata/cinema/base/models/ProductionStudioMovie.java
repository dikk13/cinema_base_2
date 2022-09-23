package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "ProductionStudioMovie")
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor

public class ProductionStudioMovie {

    @ToString.Include
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_production_studio_movie_id")
    @SequenceGenerator(name = "seq_production_studio_movie_id", sequenceName = "SEQ_PRODUCTION_STUDIO_MOVIE_ID",
    allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private ProductionStudio studio;

    @Transient
    private StudioPerformance performance;
}

