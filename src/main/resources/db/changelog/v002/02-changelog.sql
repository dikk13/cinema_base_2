-- liquibase formatted sql

-- changeset Neogetz:1668881788336-4
CREATE UNIQUE INDEX "IX_pk_persons_marriage" ON persons_marriage (person_id, human_id);

-- changeset Neogetz:1668881788336-1
ALTER TABLE collection
    ALTER COLUMN enable SET NOT NULL;

-- changeset Neogetz:1668881788336-2
ALTER TABLE history
    ALTER COLUMN history_type DROP NOT NULL;

-- changeset Neogetz:1668881788336-3
ALTER TABLE movie
    ALTER COLUMN time SET NOT NULL;

