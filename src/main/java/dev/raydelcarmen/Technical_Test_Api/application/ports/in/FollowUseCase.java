package dev.raydelcarmen.Technical_Test_Api.application.ports.in;

public interface FollowUseCase {
    void followUser(String followerUsername, String followeeUsername);
}
