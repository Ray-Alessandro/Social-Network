package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FollowServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FollowService followService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void followUser_Success() {
        User follower = new User("Alicia");
        User followee = new User("Iván");

        when(userRepository.findByUsername("Alicia")).thenReturn(follower);
        when(userRepository.findByUsername("Iván")).thenReturn(followee);

        String result = followService.followUser("Alicia", "Iván");

        assertTrue(result.contains("Alicia empezó a seguir a Iván"), "El mensaje de seguimiento es incorrecto");
        verify(userRepository).save(follower);
    }

    @Test
    void followUser_UserNotFound() {
        when(userRepository.findByUsername("Alicia")).thenReturn(new User("Alicia"));
        when(userRepository.findByUsername("Fonso")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> followService.followUser("Alicia", "Fonso"), "Se esperaba excepción de usuario no encontrado");

        verify(userRepository, never()).save(any());
    }

    @Test
    void followUser_AlreadyFollowing() {
        User follower = new User("Alicia");
        User followee = new User("Iván");
        follower.follow(followee);

        when(userRepository.findByUsername("Alicia")).thenReturn(follower);
        when(userRepository.findByUsername("Iván")).thenReturn(followee);

        String result = followService.followUser("Alicia", "Iván");

        assertTrue(result.contains("Alicia ya está siguiendo a @Iván"), "El mensaje de seguimiento ya existente es incorrecto");
        verify(userRepository, never()).save(any());
    }
}
