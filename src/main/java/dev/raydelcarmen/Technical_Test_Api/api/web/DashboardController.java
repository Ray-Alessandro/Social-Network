package dev.raydelcarmen.Technical_Test_Api.api.web;

import dev.raydelcarmen.Technical_Test_Api.application.ports.in.DashboardUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Tag(name = "Dashboard Controller", description = "Controller to handle dashboard operations")
@RestController
@RequestMapping("/api/v1")
public class DashboardController {
    @Autowired
    private DashboardUseCase dashboardUseCase;

    @Operation(summary = "Endpoint that allows a user to see his followees' posts")
    @GetMapping("/dashboard")
    public ResponseEntity<List<String>> dashboard(@RequestParam String username) {
        try {
            return ResponseEntity.ok(dashboardUseCase.getUserDashboard(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
        }
    }
}
