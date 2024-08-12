package dev.raydelcarmen.Technical_Test_Api.api.web;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.PostUseCase;
import dev.raydelcarmen.Technical_Test_Api.domain.model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Post Controller", description = "Controller to handle post operations")
@RestController
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostUseCase postUseCase;

    @Operation(summary = "Endpoint that allows a user to post a message")
    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestBody Post post) {
        try {
            return ResponseEntity.ok(postUseCase.createPost(post.getUsername(), post.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
