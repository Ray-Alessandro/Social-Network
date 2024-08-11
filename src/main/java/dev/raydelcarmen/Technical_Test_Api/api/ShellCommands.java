package dev.raydelcarmen.Technical_Test_Api.api;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.DashboardUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.FollowUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.PostUseCase;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

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
    public void post(String username, String message) {
        try {
            postUseCase.createPost(username, message);
            System.out.println(username + " posted -> \"" + message + "\"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("Follow a user")
    public void follow(String follower, String followee) {
        try {
            followUseCase.followUser(follower, followee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("View a user's dashboard")
    public void dashboard(String username) {
        try {
            var posts = dashboardUseCase.getUserDashboard(username);
            posts.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}