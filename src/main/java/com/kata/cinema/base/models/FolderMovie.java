package com.kata.cinema.base.models;


import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.Privacy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;





//@NamedEntityGraph(name = "graph_FolderMovie.Movies.Genres.Persons",
//        attributeNodes = @NamedAttributeNode(value = "movies", subgraph = "subgraph_FolderMovie.Movies.Genres"),
//        subgraphs = {
//                @NamedSubgraph(name = "subgraph_FolderMovie.Movies.Genres",
//                        attributeNodes = {
//                        @NamedAttributeNode(value = "genres"),
//                        @NamedAttributeNode(value = "moviePerson", subgraph = "subgraph_Movies.Persons")}),
//                @NamedSubgraph(name = "subgraph_Movies.Persons", attributeNodes = @NamedAttributeNode(value = "person"))
//        })

//@NamedEntityGraph(name = "FolderMovieDTO",
//        attributeNodes = @NamedAttributeNode(value = "movies", subgraph = "sub_movies"),
//        subgraphs = {
//            @NamedSubgraph(name = "sub_movies", attributeNodes = {
//                    @NamedAttributeNode(value = "genres"),
//                    @NamedAttributeNode(value = "moviePerson", subgraph = "sub_persons")}),
//            @NamedSubgraph(name = "sub_persons", attributeNodes = @NamedAttributeNode(value = "person"))
//            })




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
    private Category category;

    @Column(name = "privacy")
    private Privacy privacy;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    protected User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "folders_movies_to_movie",
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

