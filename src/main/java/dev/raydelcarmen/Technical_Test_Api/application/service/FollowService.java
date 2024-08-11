package dev.raydelcarmen.Technical_Test_Api.application.service;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.FollowUseCase;
import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.config.exception.UserNotFoundException;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;

public class FollowService implements FollowUseCase {

    private final UserRepository userRepository;

    public FollowService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void followUser(String followerUsername, String followeeUsername) {
        User follower = userRepository.findByUsername(followerUsername);
        User followee = userRepository.findByUsername(followeeUsername);
        if (follower == null) {
            throw new UserNotFoundException(followerUsername);
        }
        if (followee == null) {
            throw new UserNotFoundException(followeeUsername);
        }
        if (!follower.isFollowing(followee)) {
            follower.follow(followee);
            userRepository.save(follower);
            System.out.println(followerUsername + " started following " + followeeUsername);
        } else {
            System.out.println(followerUsername + " is already following " + followeeUsername);
        }
    }
}