package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "topic")
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_score_id")
    @SequenceGenerator(name = "seq_score_id", sequenceName = "SEQ_SCORE_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @Column(name = "html_body")
    @Lob
    private String htmlBody;

}
