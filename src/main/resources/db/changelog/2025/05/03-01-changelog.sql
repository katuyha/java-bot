-- liquibase formatted sql

-- changeset Замякиш:1746264374448-1
CREATE SEQUENCE IF NOT EXISTS jokes_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Замякиш:1746264374448-2
CREATE TABLE jokes
(
    id      BIGINT NOT NULL,
    title   VARCHAR(255),
    content VARCHAR(255),
    CONSTRAINT pk_jokes PRIMARY KEY (id)
);

