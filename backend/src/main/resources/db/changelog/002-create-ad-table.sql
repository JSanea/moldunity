CREATE TABLE IF NOT EXISTS ads (
    id BIGSERIAL     PRIMARY KEY,
    offer_type       VARCHAR(16)  NOT NULL,
    title            VARCHAR(128) NOT NULL,
    category_name    VARCHAR(32)  NOT NULL,
    subcategory_name VARCHAR(32)  NOT NULL,
    username         VARCHAR(32)  NOT NULL,
    country          VARCHAR(32)  NOT NULL,
    location         VARCHAR(64)  NOT NULL,
    description      VARCHAR(2048),
    price            INTEGER       NOT NULL CHECK (price > 0),
    views            INTEGER DEFAULT 0,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    republished_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    search_vector TSVECTOR GENERATED ALWAYS AS (
        setweight(to_tsvector('english', coalesce(title, '')), 'A') ||
        setweight(to_tsvector('english', coalesce(description, '')), 'B')
    ) STORED
);






