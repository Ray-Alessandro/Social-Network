package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.domain.model.Post;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.NoPostsFoundException;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.NoUsersFollowedException;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DashboardServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserDashboard_Success() {
        User follower = new User("Alicia");
        User followee = new User("Iván");

        Post post1 = new Post("Iván", "Hoy puede ser un gran dia");
        Post post2 = new Post("Iván", "Para casa ya, media jornada, 12h");
        followee.addPost(post1);
        followee.addPost(post2);

        follower.follow(followee);

        when(userRepository.findByUsername("Alicia")).thenReturn(follower);

        List<String> dashboard = dashboardService.getUserDashboard("Alicia");

        assertEquals(2, dashboard.size());

        assertTrue(dashboard.stream().anyMatch(post -> post.contains("Hoy puede ser un gran dia")),
                "El mensaje 'Hoy puede ...' no está presente en el dashboard");

        assertTrue(dashboard.stream().anyMatch(post -> post.contains("Para casa ya, media jornada, 12h")),
                "El mensaje 'Para casa ...' no está presente en el dashboard");
    }

    @Test
    void getUserDashboard_UserNotFound() {
        when(userRepository.findByUsername("Fonso")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> dashboardService.getUserDashboard("Fonso"), "Se esperaba excepción de usuario no encontrado");
    }

    @Test
    void getUserDashboard_NoUsersFollowed() {
        User user = new User("Alfonso");

        when(userRepository.findByUsername("Alfonso")).thenReturn(user);

        assertThrows(NoUsersFollowedException.class, () -> dashboardService.getUserDashboard("Alfonso"), "Se esperaba excepción de no seguir a ningún usuario");
    }

    @Test
    void getUserDashboard_NoPostsFound() {
        User follower = new User("Alicia");
        User followee = new User("Iván");

        follower.follow(followee);

        when(userRepository.findByUsername("Alicia")).thenReturn(follower);

        assertThrows(NoPostsFoundException.class,
                () -> dashboardService.getUserDashboard("Alicia"),
                "Se esperaba excepción de que no se encontraron posts");
    }

}
