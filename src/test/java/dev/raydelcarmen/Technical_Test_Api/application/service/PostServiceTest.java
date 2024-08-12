package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost() {

        User user = new User("Alfonso");
        when(userRepository.findByUsername("Alfonso")).thenReturn(user);

        String message = "Hola Mundo";

        String result = postService.createPost("Alfonso", message);

        assertTrue(result.contains(message), "El resultado del post no contiene el mensaje correcto");

        assertTrue(user.getPosts().stream()
                        .anyMatch(post -> post.getMessage().equals(message)),
                "El mensaje no se ha añadido al timeline del usuario");

        verify(userRepository).save(user);
    }
}