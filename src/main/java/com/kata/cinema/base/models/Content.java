package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Content {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_content_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "movies_id")
    private Movie movie;

    @Column(name = "content_url")
    private String content_url;



    @Column(name = "type")
    private String type;


}

