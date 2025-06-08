-- liquibase formatted sql

-- changeset Замякиш:1748608990800-1
CREATE SEQUENCE IF NOT EXISTS role_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Замякиш:1748608990800-3
CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Замякиш:1748608990800-5
CREATE TABLE roles
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

-- changeset Замякиш:1748608990800-6
CREATE TABLE users
(
    id       BIGINT  NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    expired  BOOLEAN NOT NULL,
    locked   BOOLEAN NOT NULL,
    enabled  BOOLEAN NOT NULL,
    role_id  BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);


-- changeset Замякиш:1748608990800-8
ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

