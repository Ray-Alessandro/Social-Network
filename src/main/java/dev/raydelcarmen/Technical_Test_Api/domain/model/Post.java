package dev.raydelcarmen.Technical_Test_Api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 1, max = 20, message = "Username must be between 1 and 20 characters")
    private final String username;

    @NotBlank(message = "Message cannot be blank")
    @Size(min = 1, max = 300, message = "Message must be between 1 and 300 characters")
    private final String message;

    @JsonIgnore
    private final LocalDateTime timestamp;

    public Post(String username, String message) {
        this.message = message;
        this.username = username;
        this.timestamp = LocalDateTime.now();
    }
}
