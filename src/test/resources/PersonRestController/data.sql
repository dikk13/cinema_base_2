INSERT INTO award_ceremony (id, DATE_EVENT)
VALUES ('100', '2011-02-04');

INSERT INTO persons (id, original_first_name, original_last_name, first_name)
VALUES ('100', 'Персона', '100', 'Тест');

INSERT INTO movie (id, name)
VALUES ('100', 'Фильм100');

INSERT INTO nomination (id, name)
VALUES ('100', 'Номинация100');

INSERT INTO award_ceremony_result (id, person, movie, nomination_id, award_ceremony_id, nomination_status)
VALUES ('100', '100', '100', '100', '100', '1');