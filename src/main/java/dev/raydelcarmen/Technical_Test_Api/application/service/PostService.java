package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.PostUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.UserNotFoundException;
import dev.raydelcarmen.Technical_Test_Api.domain.model.Post;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;

public class PostService implements PostUseCase {

    private final UserRepository userRepository;

    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createPost(String username, String message) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        user.addPost(new Post(username, message));
        userRepository.save(user);
    }
}