package dev.raydelcarmen.Technical_Test_Api.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
public class User {
    private final String username;
    private final List<Post> posts;
    private final Set<User> following;

    public User(String username) {
        this.username = username;
        this.posts = new ArrayList<>();
        this.following = new HashSet<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void follow(User user) {
        following.add(user);
    }

    public boolean isFollowing(User user) {
        return following.contains(user);
    }

    public List<Post> getTimeline() {
        List<Post> timeline = new ArrayList<>(posts);
        for (User followedUser : following) {
            timeline.addAll(followedUser.getPosts());
        }
        return timeline;
    }
}