CREATE TABLE watch_rooms (
    id BIGSERIAL PRIMARY KEY,
    room_code VARCHAR(12) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    owner_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_watch_rooms_owner
        FOREIGN KEY (owner_id)
        REFERENCES users(id)
);