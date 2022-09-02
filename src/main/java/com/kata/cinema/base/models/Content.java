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
    @SequenceGenerator(name="seq_content_id",sequenceName="SEQ_CONTENT_ID", allocationSize=1)
    private Long id;


    @OneToOne
    @JoinColumn(name = "movies_id")
    private Movie movie;

    @Column(name = "content_url")
    private String content_url;



    @Column(name = "type")
    private String type;


}

