package com.watchtogether.room;

import com.watchtogether.user.User;
import com.watchtogether.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class RoomService {

    private final WatchRoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomMemberRepository roomMemberRepository;



    public RoomService(
            WatchRoomRepository roomRepository,
            UserRepository userRepository,
            RoomMemberRepository roomMemberRepository
    ) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.roomMemberRepository = roomMemberRepository;
    }

    @Transactional
    public RoomResponse createRoom(
            CreateRoomRequest request,
            String username
    ) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        String roomCode = generateRoomCode();

        WatchRoom room = new WatchRoom(
                roomCode,
                request.name(),
                owner
        );

        WatchRoom savedRoom = roomRepository.save(room);
        RoomMember membership = new RoomMember(savedRoom, owner);
        roomMemberRepository.save(membership);

        return new RoomResponse(
                savedRoom.getId(),
                savedRoom.getRoomCode(),
                savedRoom.getName()
        );


    }
    @Transactional(readOnly = true)
    public RoomResponse getRoom(
            String roomCode,
            String username
    ) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        WatchRoom room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() ->
                        new IllegalArgumentException("Room not found"));

        boolean isMember = roomMemberRepository
                .existsByRoomAndUser(room, user);

        if (!isMember) {
            throw new IllegalArgumentException("You are not a member of this room");
        }

        return new RoomResponse(
                room.getId(),
                room.getRoomCode(),
                room.getName()
        );
    }

    @Transactional
    public RoomResponse joinRoom(
            JoinRoomRequest request,
            String username
    ) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        WatchRoom room = roomRepository.findByRoomCode(request.roomCode())
                .orElseThrow(() ->
                        new IllegalArgumentException("Room not found"));

        if (!roomMemberRepository.existsByRoomAndUser(room, user)) {
            RoomMember membership = new RoomMember(room, user);
            roomMemberRepository.save(membership);
        }

        return new RoomResponse(
                room.getId(),
                room.getRoomCode(),
                room.getName()
        );
    }


    private String generateRoomCode() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }
}