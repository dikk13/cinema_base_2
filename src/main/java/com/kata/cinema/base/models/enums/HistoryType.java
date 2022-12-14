package com.kata.cinema.base.models.enums;

public enum HistoryType {
    PERSON(Values.PERSON),
    MOVIE(Values.MOVIE);

    HistoryType(String val) {
        if (!this.name().equals(val)) {
            throw new IllegalArgumentException("Incorrect use of HistoryType");
        }
    }

    public static class Values {
        public static final String PERSON = "PERSON";
        public static final String MOVIE = "MOVIE";
    }
}
