package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.DashboardUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.shared.exception.UserNotFoundException;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardService implements DashboardUseCase {

    private final UserRepository userRepository;

    public DashboardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getUserDashboard(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user.getTimeline().stream()
                .map(post -> "\""+post.getMessage() + "\" @" + post.getUsername() + " @" +
                        post.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm")))
                .collect(Collectors.toList());
    }
}