package com.watchtogether.auth;

public record RegisterRequest(
        String username,
        String email,
        String password
) {
}