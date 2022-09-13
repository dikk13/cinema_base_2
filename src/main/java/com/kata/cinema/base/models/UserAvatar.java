package com.kata.cinema.base.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "user_avatar")
@NoArgsConstructor
//TODO без наследования
public class UserAvatar extends User {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAvatar userAvatar;

    @Column(name = "avatar_url")
    private String avatarUrl;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserAvatar that = (UserAvatar) o;
        return Objects.equals(userAvatar, that.userAvatar) && Objects.equals(avatarUrl, that.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userAvatar, avatarUrl);
    }

    @Override
    public String toString() {
        return "UserAvatar{" +
                "user=" + userAvatar +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
