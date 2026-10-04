package com.watchtogether.auth;

public record LoginRequest(
        String username,
        String password
) {
}