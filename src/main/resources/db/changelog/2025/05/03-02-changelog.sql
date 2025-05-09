-- liquibase formatted sql

-- changeset Замякиш:1746270344082-1
CREATE SEQUENCE IF NOT EXISTS teach_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Замякиш:1746270344082-2
CREATE TABLE challenges_jokes
(
    id        BIGINT NOT NULL,
    user_id   BIGINT,
    date_time TIMESTAMP WITHOUT TIME ZONE,
    jokes_id  BIGINT,
    CONSTRAINT pk_challengesjokes PRIMARY KEY (id)
);

-- changeset Замякиш:1746270344082-3
ALTER TABLE challenges_jokes
    ADD CONSTRAINT FK_CHALLENGESJOKES_ON_JOKESID FOREIGN KEY (jokes_id) REFERENCES jokes (id);

