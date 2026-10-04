package com.watchtogether.room;

import com.watchtogether.user.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "watch_rooms")
public class WatchRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_code", nullable = false, unique = true, length = 12)
    private String roomCode;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected WatchRoom() {
    }

    public WatchRoom(String roomCode, String name, User owner) {
        this.roomCode = roomCode;
        this.name = name;
        this.owner = owner;
        this.createdAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}