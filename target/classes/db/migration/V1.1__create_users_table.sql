CREATE TABLE users (
    id              BIGSERIAL NOT NULL,
    username        VARCHAR(50) NOT NULL UNIQUE,
    password        VARCHAR(256) NOT NULL, -- hashed password
    email           VARCHAR(100) NOT NULL UNIQUE,
    registration_date DATE DEFAULT CURRENT_DATE
);
ALTER TABLE users ADD CONSTRAINT user_pk PRIMARY KEY (id);