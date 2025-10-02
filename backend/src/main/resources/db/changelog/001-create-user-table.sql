CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username     VARCHAR(32)  NOT NULL UNIQUE,
    password     VARCHAR(256) NOT NULL,
    email        VARCHAR(128) NOT NULL UNIQUE,
    role         VARCHAR(16)  NOT NULL,
    country      VARCHAR(32)  NOT NULL,
    location     VARCHAR(64)  NOT NULL,
    phone        VARCHAR(64),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

