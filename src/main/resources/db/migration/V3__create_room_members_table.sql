CREATE TABLE room_members (
    id BIGSERIAL PRIMARY KEY,
    room_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    joined_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_room_members_room
        FOREIGN KEY (room_id)
        REFERENCES watch_rooms(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_room_members_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_room_members_room_user
        UNIQUE (room_id, user_id)
);