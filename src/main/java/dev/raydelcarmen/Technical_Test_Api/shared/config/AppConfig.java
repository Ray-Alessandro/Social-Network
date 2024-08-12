package dev.raydelcarmen.Technical_Test_Api.shared.config;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.DashboardUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.FollowUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.in.PostUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.application.service.DashboardService;
import dev.raydelcarmen.Technical_Test_Api.application.service.FollowService;
import dev.raydelcarmen.Technical_Test_Api.application.service.PostService;
import dev.raydelcarmen.Technical_Test_Api.infrastructure.repository.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public PostUseCase postUseCase(UserRepository userRepository) {
        return new PostService(userRepository);
    }

    @Bean
    public FollowUseCase followUseCase(UserRepository userRepository) {
        return new FollowService(userRepository);
    }

    @Bean
    public DashboardUseCase dashboardUseCase(UserRepository userRepository) {
        return new DashboardService(userRepository);
    }
}
