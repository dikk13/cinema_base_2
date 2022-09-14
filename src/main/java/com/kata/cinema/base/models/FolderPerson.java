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
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "folders_persons_to_person",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set <Person> folderPersonsSet = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


    public void addPersonToFolder (Person person) {
        folderPersonsSet.add(person);
    }

    public void removePersonFromFolder (Person person) {
        folderPersonsSet.remove(person);
    }

    public Set<Person> getFolderPersonsSet() {
        return folderPersonsSet;
    }

    public void setFolderPersonsSet(Set<Person> folderPersonsSet) {
        this.folderPersonsSet = folderPersonsSet;
    }
}