package dev.raydelcarmen.Technical_Test_Api.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {
    private final String message;
    private final String username;
    private final LocalDateTime timestamp;

    public Post(String username, String message) {
        this.message = message;
        this.username = username;
        this.timestamp = LocalDateTime.now();
    }
}
