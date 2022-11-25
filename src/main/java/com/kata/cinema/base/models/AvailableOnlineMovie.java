package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Table(name = "available_online_movie")
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class AvailableOnlineMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_available_online_movie_id")
    @SequenceGenerator(name = "seq_available_online_movie_id", sequenceName = "SEQ_AVAILABLE_ONLINE_MOVIE_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name="rental_price")
    private Integer rentalPrice;//стоимость аренды
    @Column(name="buy_price")
    private Integer buyPrice;//цена покупки
    @Column(name = "available_plus")
    private Boolean availablePlus;//имеет доступ
    @Column(name = "enabled",nullable = false, columnDefinition = "boolean default true")
    private boolean enabled=Boolean.TRUE;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;


    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "availableOnlineMovie")
    private List<PurchasedMovie> purchasedMovie;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableOnlineMovie that = (AvailableOnlineMovie) o;
        return isEnabled() == that.isEnabled() && Objects.equals(getId(), that.getId()) && Objects.equals(getRentalPrice(), that.getRentalPrice()) && Objects.equals(getBuyPrice(), that.getBuyPrice()) && Objects.equals(getAvailablePlus(), that.getAvailablePlus()) && Objects.equals(getMovie(), that.getMovie()) && Objects.equals(getPurchasedMovie(), that.getPurchasedMovie());
    }

    public AvailableOnlineMovie(boolean enabled) {
        this.enabled = enabled;
    }
}

