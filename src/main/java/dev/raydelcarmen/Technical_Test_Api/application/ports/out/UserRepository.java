package dev.raydelcarmen.Technical_Test_Api.application.ports.out;

import dev.raydelcarmen.Technical_Test_Api.domain.model.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}