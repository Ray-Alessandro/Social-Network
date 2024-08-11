package dev.raydelcarmen.Technical_Test_Api.application.ports.in;

import java.util.List;

public interface DashboardUseCase {
    List<String> getUserDashboard(String username);
}