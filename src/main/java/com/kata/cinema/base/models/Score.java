package com.kata.cinema.base.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movies movies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private List <User> user;

    @Column(name = "score")
    private int score;

    public Score() {
    }


}
