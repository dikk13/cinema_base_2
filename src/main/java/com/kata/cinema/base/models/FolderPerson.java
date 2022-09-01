package com.kata.cinema.base.models;

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

    @Column(name = "favourites", nullable = false)    // УЗНАТЬ ПОДРОБНЕЕ, ЧТО ЕСТЬ флаг для избранного фолдера поумолчанию :))
    @Getter
    @Setter
    protected Boolean favourites;

    @Column(name = "privacy", nullable = false, length = 7)
    @Getter
    @Setter
    protected String privacy;

    @Column(name = "name", nullable = false, length = 30)
    @Getter
    @Setter
    protected String name;

    @Column(name = "description", nullable = true, length = 100)
    @Getter
    @Setter
    protected String description;

    //  Связь между папками пользователя и персонами, в каждой папке пользователя может быть много персон
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   // ВОТ ЭТО НАДО РЕШИТЬ LAZY НЕ РАБОТАЕТ
    @JoinTable (name = "folder_persons_to_person", joinColumns = @JoinColumn(name = "folder_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set <Person> folderPersonsSet = new HashSet<>();


    // Связь между пользователем и папкой у одного пользователя может быть много папок
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;


    public FolderPerson(Boolean favourites, String privacy, String name, String description, User user) {
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