package com.kata.cinema.base.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
@Setter
@ToString
@Table(name = "folders_persons")
@NoArgsConstructor
public class FolderPerson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_folders_persons_id")
    @SequenceGenerator(name = "seq_folders_persons_id", sequenceName = "SEQ_FOLDERPERSONS_ID", allocationSize = 1)
    private Long id;

    @Column(name = "favourites", nullable = false)
    protected Boolean favourites;

    @Column(name = "privacy", nullable = false, length = 7)
    protected String privacy;

    @Column(name = "name", nullable = false, length = 30)
    protected String name;

    @Column(name = "description", nullable = true, length = 100)
    protected String description;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderPerson that = (FolderPerson) o;
        return Objects.equals(id, that.id) && favourites == that.favourites && Objects.equals(privacy, that.privacy) &&
                Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }
}