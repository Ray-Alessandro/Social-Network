package dev.raydelcarmen.Technical_Test_Api.application.ports.in;

public interface PostUseCase {
    String createPost(String username, String message);
}