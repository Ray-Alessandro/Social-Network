package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.DashboardUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.NoPostsFoundException;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.NoUsersFollowedException;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.UserNotFoundException;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService implements DashboardUseCase {

    private final UserRepository userRepository;

    public DashboardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getUserDashboard(String username) {
        User user = userRepository.findByUsername(username);

        validateConditions(user, username);

        return user.getTimeline().stream()
                .map(post -> "\""+post.getMessage() + "\" @" + post.getUsername() + " @" +
                        post.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm")))
                .collect(Collectors.toList());
    }

    private void validateConditions(User user, String username) {
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        else if (user.getFollowing().isEmpty()) {
            throw new NoUsersFollowedException(username);
        }
        else if (user.getTimeline().isEmpty()) {
            throw new NoPostsFoundException();
        }
    }
}