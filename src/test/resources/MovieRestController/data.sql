CREATE TABLE award_ceremony_result
(
    id                INT NOT NULL,
    person            VARCHAR(255),
    movie             INT,
    nomination_id     INT,
    award_ceremony_id INT,
    nomination_status INT,
    PRIMARY KEY (id)
);

INSERT INTO award_ceremony_result (id, person, movie, nomination_id, award_ceremony_id, nomination_status)
VALUES ('100', '100', '100', '100', '100', '1');

CREATE TABLE movie
(
    id   INT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO movie (id, name)
VALUES ('100', 'Фильм100');

CREATE TABLE award_ceremony
(
    id         INT NOT NULL,
    DATE_EVENT DATE,
    PRIMARY KEY (id)
);

INSERT INTO award_ceremony (id, DATE_EVENT)
VALUES ('100', '2011-02-04');