CREATE TABLE assets (
    id              BIGSERIAL NOT NULL,
    name            VARCHAR(100) NOT NULL UNIQUE,
    type            VARCHAR(50) NOT NULL -- for example: ‘Stock’, ‘Bond’, ‘Crypto’, etc.
);
ALTER TABLE assets ADD CONSTRAINT asset_pk PRIMARY KEY (id);