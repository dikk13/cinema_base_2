package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductionStudio {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_production_studio_id")
    @SequenceGenerator(name = "seq_production_studio_id",sequenceName = "SEQ_PRODUCTION_STUDIO_ID", allocationSize = 1)
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
        if (!super.equals(o)) return false;
        ProductionStudio that = (ProductionStudio) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) && Objects.equals(dateFoundation, that.dateFoundation);
    }
}

