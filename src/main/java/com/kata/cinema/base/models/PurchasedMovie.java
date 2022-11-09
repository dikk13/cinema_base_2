package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.PurchaseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;


@Table(name = "purchased_movie")
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class PurchasedMovie { //купленный фильм

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_purchased_movie_id")
    @SequenceGenerator(name = "seq_purchased_movie_id", sequenceName = "SEQ_PURCHASED_MOVIE_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "available_online_movie_id")
    private AvailableOnlineMovie availableOnlineMovie;

    @Column
    LocalDate endDate;


    @Column
    @Enumerated(EnumType.STRING)
    private PurchaseType purchase;// тип покупки

}
