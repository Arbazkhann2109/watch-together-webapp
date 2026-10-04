package com.watchtogether.room;

import com.watchtogether.user.User;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "room_members",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_room_members_room_user",
                        columnNames = {"room_id", "user_id"}
                )
        }
)
public class RoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private WatchRoom room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "joined_at", nullable = false)
    private OffsetDateTime joinedAt;

    protected RoomMember() {
    }

    public RoomMember(WatchRoom room, User user) {
        this.room = room;
        this.user = user;
        this.joinedAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public WatchRoom getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public OffsetDateTime getJoinedAt() {
        return joinedAt;
    }
}