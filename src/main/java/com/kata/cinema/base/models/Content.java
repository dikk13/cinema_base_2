package com.kata.cinema.base.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Content {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "movies_id")
    private Movies movies;

    @Column(name = "content_url")
    private String content_url;



    @Column(name = "type")
    private String type;

    public Content() {
    }

}

