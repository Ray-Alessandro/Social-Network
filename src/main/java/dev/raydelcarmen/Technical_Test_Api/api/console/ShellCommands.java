package dev.raydelcarmen.Technical_Test_Api.api.console;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.DashboardUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.FollowUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.PostUseCase;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommands {

    private final PostUseCase postUseCase;
    private final FollowUseCase followUseCase;
    private final DashboardUseCase dashboardUseCase;

    public ShellCommands(PostUseCase postUseCase, FollowUseCase followUseCase, DashboardUseCase dashboardUseCase) {
        this.postUseCase = postUseCase;
        this.followUseCase = followUseCase;
        this.dashboardUseCase = dashboardUseCase;
    }

    @ShellMethod("Post a message as a user")
    public void post(@ShellOption String username, @ShellOption(arity = 120) String[] messageParts) {
        try {
            String fullMessage = String.join(" ", messageParts);

            System.out.println(
                    postUseCase.createPost(cleanUsername(username), fullMessage)
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("Follow a user")
    public void follow(@ShellOption String follower,@ShellOption String followee) {
        try {
            System.out.println(
                    followUseCase.followUser(cleanUsername(follower), cleanUsername(followee))
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("View a user's dashboard")
    public void dashboard(@ShellOption String username) {
        try {
            var posts = dashboardUseCase.getUserDashboard(cleanUsername(username));
            posts.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String cleanUsername(@ShellOption String username) {
        return username.startsWith("@") ? username.substring(1) : username;
    }
}