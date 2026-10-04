package com.watchtogether.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchRoomRepository extends JpaRepository<WatchRoom, Long> {

    Optional<WatchRoom> findByRoomCode(String roomCode);

    boolean existsByRoomCode(String roomCode);
}