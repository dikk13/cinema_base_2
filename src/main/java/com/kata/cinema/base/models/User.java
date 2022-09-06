package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstName")
    private String first_name;

    @Column(name = "lastName")
    private String last_name;

    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "roleId")
    private int roleId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAvatar> userAvatars;

    @OneToMany(mappedBy = "user")
    private List<FolderMovie> folderMovies;

    @OneToMany(mappedBy = "user")
    private List<News> news;

    @OneToMany(mappedBy = "user")
    private List<Score> scores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && Objects.equals(email, user.email) && Objects.equals(first_name, user.first_name)
                && Objects.equals(last_name, user.last_name) && Objects.equals(password, user.password) && Objects.equals(birthday, user.birthday)
                && Objects.equals(userAvatars, user.userAvatars) && Objects.equals(folderMovies, user.folderMovies) && Objects.equals(news, user.news) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, first_name, last_name, password, birthday, roleId, userAvatars, folderMovies, news, role);
    }
}
