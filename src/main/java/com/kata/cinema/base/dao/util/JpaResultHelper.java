package com.kata.cinema.base.dao.util;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaResultHelper {

    public static <X> Optional<X> jpaResultHelper(TypedQuery<X> typedQuery) {
        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
