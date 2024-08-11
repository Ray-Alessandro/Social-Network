package dev.raydelcarmen.Technical_Test_Api.application.ports.in;

public interface PostUseCase {
    void createPost(String username, String message);
}