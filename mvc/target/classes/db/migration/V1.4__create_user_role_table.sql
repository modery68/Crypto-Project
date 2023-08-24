DROP TABLE IF EXISTS system_users CASCADE;

CREATE TABLE system_users (
                              id              BIGSERIAL NOT NULL,
                              name            VARCHAR(30) NOT NULL UNIQUE,
                              password        VARCHAR(64),
                              secret_key      VARCHAR(512),
                              first_name      VARCHAR(30),
                              last_name       VARCHAR(30),
                              email           VARCHAR(50) NOT NULL UNIQUE
);

ALTER TABLE system_users ADD CONSTRAINT system_users_pk PRIMARY KEY (id);

CREATE TABLE roles (
                       id                  BIGSERIAL NOT NULL,
                       name                VARCHAR(30) NOT NULL UNIQUE,
                       allowed_resource    VARCHAR(200),
                       allowed_read        BOOLEAN not null default FALSE,
                       allowed_create      BOOLEAN not null default FALSE,
                       allowed_update      BOOLEAN not null default FALSE,
                       allowed_delete      BOOLEAN not null default FALSE
);

ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY (id);

CREATE TABLE system_users_roles(
                                   user_id     BIGINT NOT NULL,
                                   role_id     BIGINT NOT NULL

);

ALTER TABLE system_users_roles
    ADD CONSTRAINT system_users_fk FOREIGN KEY (user_id)
        REFERENCES system_users ( id );
ALTER TABLE system_users_roles
    ADD CONSTRAINT roles_fk foreign key (role_id)
        REFERENCES roles ( id );

