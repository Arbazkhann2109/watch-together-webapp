package com.watchtogether.room;

import com.watchtogether.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {

    boolean existsByRoomAndUser(WatchRoom room, User user);
    boolean existsByRoom_RoomCodeAndUser_Username(
            String roomCode,
            String username
    );
}