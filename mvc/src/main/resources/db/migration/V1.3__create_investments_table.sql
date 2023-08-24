CREATE TABLE investments (
    id              BIGSERIAL NOT NULL,
    user_id         BIGINT NOT NULL,
    asset_id        BIGINT NOT NULL,
    quantity        NUMERIC(10, 2) NOT NULL,
    purchase_price  NUMERIC(10, 2) NOT NULL,
    purchase_date   DATE NOT NULL
);
ALTER TABLE investments ADD CONSTRAINT investment_pk PRIMARY KEY (id);
ALTER TABLE investments
    ADD CONSTRAINT investment_user_fk FOREIGN KEY (user_id)
        REFERENCES users (id);
ALTER TABLE investments
    ADD CONSTRAINT investment_asset_fk FOREIGN KEY (asset_id)
        REFERENCES assets (id);