package com.kata.cinema.base.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "media_franchise")
@NoArgsConstructor
@AllArgsConstructor
public class MediaFranchise {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_media_franchise_id")
    @SequenceGenerator(name = "seq_media_franchise_id", sequenceName = "SEQ_MEDIA_FRANCHISE_ID", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaFranchise that = (MediaFranchise) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}