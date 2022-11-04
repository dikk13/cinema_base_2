package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class AvailableOnlineMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_available_online_movie_id")
    @SequenceGenerator(name = "seq_available_online_movie_id", sequenceName = "SEQ_AVAILABLE_ONLINE_MOVIE_ID", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name="rental_price")
    Integer rentalPrice;//стоимость аренды

    @Column(name="buy_price")
    Integer buyPrice;//цена покупки
    @Column(name = "available_plus")
    Boolean availablePlus;//имеет доступ
    @Column(name = "enabled")
    Boolean enabled = true;

    @OneToOne(optional = false, mappedBy = "availableOnlineMovie")
    private Movie movie;

    @OneToMany(mappedBy = "availableOnlineMovie", fetch = FetchType.LAZY)
    private List<PurchasedMovie> purchasedMovie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableOnlineMovie that = (AvailableOnlineMovie) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getRentalPrice(), that.getRentalPrice()) && Objects.equals(getBuyPrice(), that.getBuyPrice()) && Objects.equals(getAvailablePlus(), that.getAvailablePlus()) && Objects.equals(getEnabled(), that.getEnabled()) && Objects.equals(getMovie(), that.getMovie());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AvailableOnlineMovie{" +
                "id=" + id +
                ", rentalPrice=" + rentalPrice +
                ", buyPrice=" + buyPrice +
                ", availablePlus=" + availablePlus +
                ", enabled=" + enabled +
                ", movie=" + movie +
                '}';
    }
}

