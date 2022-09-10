package com.kata.cinema.base.models;

import com.kata.cinema.base.enums.Privacy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "folders_persons")
@NoArgsConstructor
public class FolderPerson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;

    @Column(name = "favourites", nullable = false)
    @Getter
    @Setter
    protected Boolean favourites;

    @Column(name = "privacy", nullable = false, length = 7)
    @Getter
    @Setter
    protected Privacy privacy;

    @Column(name = "name", nullable = false, length = 30)
    @Getter
    @Setter
    protected String name;

    @Column(name = "description", nullable = true, length = 100)
    @Getter
    @Setter
    protected String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (name = "folder_persons_to_person", joinColumns = @JoinColumn(name = "folder_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set <Person> folderPersonsSet = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;


    public FolderPerson(Boolean favourites, Privacy privacy, String name, String description, User user) {
        this.favourites = favourites;
        this.privacy = privacy;
        this.name = name;
        this.description = description;
        this.user = user;
    }

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