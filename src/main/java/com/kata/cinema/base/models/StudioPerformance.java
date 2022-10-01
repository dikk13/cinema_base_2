package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudioPerformance {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_studio_performance_id")
    @SequenceGenerator(name = "seq_studio_performance_id", sequenceName = "SEQ_STUDIO_PERFORMANCE_ID", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        StudioPerformance studioPerformance = (StudioPerformance) obj;
        return Objects.equals(id, studioPerformance.id) && Objects.equals(name, studioPerformance.name);
    }
}
