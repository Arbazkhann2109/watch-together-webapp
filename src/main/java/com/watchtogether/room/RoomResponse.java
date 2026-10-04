package com.watchtogether.room;

public record RoomResponse(
        Long id,
        String roomCode,
        String name
) {
}