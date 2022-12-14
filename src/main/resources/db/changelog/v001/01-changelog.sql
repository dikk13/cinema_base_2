-- liquibase formatted sql

-- changeset Neogetz:1668248786494-3
CREATE SEQUENCE IF NOT EXISTS seq_reactionreview_id START WITH 1 INCREMENT BY 1;

-- changeset Neogetz:1668248786494-4
CREATE TABLE reaction_review
(
    id        BIGINT NOT NULL,
    rating    VARCHAR(255),
    review_id BIGINT NOT NULL,
    user_id   BIGINT NOT NULL,
    CONSTRAINT pk_reactionreview PRIMARY KEY (id)
);

-- changeset Neogetz:1668248786494-5
CREATE UNIQUE INDEX "IX_pk_persons_marriage" ON persons_marriage (person_id, human_id);

-- changeset Neogetz:1668248786494-6
ALTER TABLE reaction_review
    ADD CONSTRAINT FK_REACTIONREVIEW_ON_REVIEW FOREIGN KEY (review_id) REFERENCES review (id);

-- changeset Neogetz:1668248786494-7
ALTER TABLE reaction_review
    ADD CONSTRAINT FK_REACTIONREVIEW_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Neogetz:1668248786494-1
ALTER TABLE collection
    ALTER COLUMN enable SET NOT NULL;

-- changeset Neogetz:1668248786494-2
ALTER TABLE movie
    ALTER COLUMN time SET NOT NULL;

