package dev.raydelcarmen.Technical_Test_Api.api.web;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.FollowUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Follow Controller", description = "Controller to handle follow operations")
@RestController
@RequestMapping("/api/v1")
public class FollowController {
    @Autowired
    private FollowUseCase followUseCase;

    @Operation(summary = "Endpoint that allows a user to follow another user")
    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestParam String followerUsername, @RequestParam String followeeUsername) {
        try {
            return ResponseEntity.ok(followUseCase.followUser(followerUsername, followeeUsername));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
