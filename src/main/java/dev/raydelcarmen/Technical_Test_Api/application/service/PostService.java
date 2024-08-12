package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.PostUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.UserNotFoundException;
import dev.raydelcarmen.Technical_Test_Api.domain.model.Post;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PostService implements PostUseCase {

    private final UserRepository userRepository;

    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createPost(String username, String message) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        user.addPost(new Post(username, message));
        userRepository.save(user);

        return (username + " posted -> \"" + message + "\" @" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}