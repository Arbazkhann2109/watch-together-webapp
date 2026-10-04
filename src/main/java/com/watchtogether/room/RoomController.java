package com.watchtogether.room;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse createRoom(
            @RequestBody CreateRoomRequest request,
            Authentication authentication
    ) {
        return roomService.createRoom(
                request,
                authentication.getName()
        );
    }

    @PostMapping("/join")
    public RoomResponse joinRoom(
            @RequestBody JoinRoomRequest request,
            Authentication authentication
    ) {
        return roomService.joinRoom(
                request,
                authentication.getName()
        );
    }

    @GetMapping("/{roomCode}")
    public RoomResponse getRoom(
            @PathVariable String roomCode,
            Authentication authentication
    ) {
        return roomService.getRoom(
                roomCode,
                authentication.getName()
        );
    }
}