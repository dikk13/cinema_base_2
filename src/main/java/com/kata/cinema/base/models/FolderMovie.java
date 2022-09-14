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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "folders_movies")
@NoArgsConstructor
public class FolderMovie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_folders_movies_id")
    @SequenceGenerator(name = "seq_folders_movies_id", sequenceName = "SEQ_FOLDERMOVIES_ID", allocationSize = 1)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "privacy")
    private String privacy;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    protected User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "folder_movies_to_movie",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderMovie that = (FolderMovie) o;
        return id == that.id && user == that.user && Objects.equals(category, that.category) && Objects.equals(privacy, that.privacy) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, user, privacy, name, description);
    }
}
