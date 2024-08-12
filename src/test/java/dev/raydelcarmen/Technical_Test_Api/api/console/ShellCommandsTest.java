package dev.raydelcarmen.Technical_Test_Api.api.console;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.DashboardUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.FollowUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.PostUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ShellCommandsTest {

    @Mock
    private PostUseCase postUseCase;

    @Mock
    private FollowUseCase followUseCase;

    @Mock
    private DashboardUseCase dashboardUseCase;

    @InjectMocks
    private ShellCommands shellCommands;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void post() {
        String username = "@Alfonso";
        String[] messageParts = {"Hola", "Mundo"};
        String fullMessage = "Hola Mundo";
        String expectedResponse = "Alfonso posted -> \"Hola Mundo\" @14:30";

        when(postUseCase.createPost("Alfonso", fullMessage)).thenReturn(expectedResponse);

        shellCommands.post(username, messageParts);

        verify(postUseCase).createPost("Alfonso", fullMessage);
    }

    @Test
    void follow() {
        String followerUsername = "@Alicia";
        String followeeUsername = "@Iván";
        String expectedResponse = "Alicia empezó a seguir a Iván";

        when(followUseCase.followUser("Alicia", "Iván")).thenReturn(expectedResponse);

        shellCommands.follow(followerUsername, followeeUsername);

        verify(followUseCase).followUser("Alicia", "Iván");
    }

    @Test
    void dashboard() {
        String username = "@Alicia";

        List<String> posts = Arrays.asList(
                "\"Hoy puede ser un gran dia\" @Iván @14:30",
                "\"Para casa ya, media jornada, 12h\" @Iván @14:30"
        );

        when(dashboardUseCase.getUserDashboard("Alicia")).thenReturn(posts);

        shellCommands.dashboard(username);

        verify(dashboardUseCase).getUserDashboard("Alicia");
    }
}
