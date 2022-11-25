-- liquibase formatted sql

-- changeset SportUnion:1668955601873-3
ALTER TABLE available_online_movie
    ADD movie_id BIGINT;

-- changeset SportUnion:1668955601873-4
CREATE UNIQUE INDEX "IX_pk_persons_marriage" ON persons_marriage (person_id, human_id);

-- changeset SportUnion:1668955601873-5
ALTER TABLE available_online_movie
    ADD CONSTRAINT FK_AVAILABLE_ONLINE_MOVIE_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset SportUnion:1668955601873-1
ALTER TABLE collection
    ALTER COLUMN enable SET NOT NULL;

-- changeset SportUnion:1668955601873-2
ALTER TABLE movie
    ALTER COLUMN time SET NOT NULL;

