package com.kata.cinema.base.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductionStudioMovie {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_production_studio_movie_id")
    @SequenceGenerator(name = "seq_production_studio_movie_id", sequenceName = "SEQ_PRODUCTION_STUDIO_MOVIE_ID",
    allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "productionStudio_id")
    private ProductionStudio studio;

    @ManyToOne
    @JoinColumn(name = "studioPerformance_id")
    private StudioPerformance performance;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductionStudioMovie productionStudioMovie = (ProductionStudioMovie) obj;
        return Objects.equals(id, productionStudioMovie.id);
    }
}
