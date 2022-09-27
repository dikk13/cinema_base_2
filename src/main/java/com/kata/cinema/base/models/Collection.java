package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_collection_id")
    @SequenceGenerator(name = "seq_collection_id", sequenceName = "SEQ_COLLECTION_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "enable")
    private boolean enable;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "collection_movie",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return enable == that.enable && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                ", movies=" + movies +
                '}';
    }
}
