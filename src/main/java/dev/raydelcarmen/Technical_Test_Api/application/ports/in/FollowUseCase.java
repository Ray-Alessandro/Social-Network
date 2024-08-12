package dev.raydelcarmen.Technical_Test_Api.application.ports.in;

public interface FollowUseCase {
    String followUser(String followerUsername, String followeeUsername);
}
