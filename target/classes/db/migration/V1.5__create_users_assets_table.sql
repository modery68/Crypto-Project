CREATE TABLE users_assets(
    user_id         BIGINT not null,
    asset_id        BIGINT not null
);

ALTER TABLE users_assets
    ADD CONSTRAINT users_fk FOREIGN KEY (user_id)
        REFERENCES users ( id );
ALTER TABLE users_assets
    ADD CONSTRAINT assets_fk foreign key (asset_id)
        REFERENCES assets ( id );