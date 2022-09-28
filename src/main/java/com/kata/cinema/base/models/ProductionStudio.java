package com.kata.cinema.base.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductionStudio {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_production_studio_id")
    @SequenceGenerator(name = "seq_production_studio_id", sequenceName = "SEQ_PRODUCTION_STUDIO_ID", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private LocalDate dateFoundation;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionStudio productionStudio = (ProductionStudio) o;
        return Objects.equals(id, productionStudio.id) && Objects.equals(name, productionStudio.name) &&
                Objects.equals(description, productionStudio.description) &&
                Objects.equals(dateFoundation, productionStudio.dateFoundation);
    }
}
