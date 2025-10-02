CREATE TABLE IF NOT EXISTS cars (
    id              BIGSERIAL   PRIMARY KEY,
    brand           VARCHAR(32) NOT NULL,
    model           VARCHAR(32) NOT NULL,
    year            INTEGER NOT NULL CHECK (year > 1900),
    fuel            VARCHAR(32) NOT NULL,
    power           INTEGER NOT NULL CHECK(power > 0),
    color           VARCHAR(32) NOT NULL,
    body            VARCHAR(32) NOT NULL,
    mileage         INTEGER NOT NULL CHECK (mileage > 0),
    gear_box        VARCHAR(32) NOT NULL,
    steering_wheel  VARCHAR(32) NOT NULL,
    engine_capacity INTEGER NOT NULL CHECK(engine_capacity > 0),
    ad_id BIGINT    NOT NULL,
    FOREIGN KEY (ad_id) REFERENCES ads(id) ON DELETE CASCADE,
    search_vector TSVECTOR GENERATED ALWAYS AS (
        setweight(to_tsvector('english', coalesce(brand, '')), 'B') ||
        setweight(to_tsvector('english', coalesce(model, '')), 'A')
    ) STORED
)
