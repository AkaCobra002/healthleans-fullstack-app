package com.healthleans.controller;

import com.healthleans.model.HealthLog;
import com.healthleans.service.HealthLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*")
public class LogController {

    private final HealthLogService logService;

    public LogController(HealthLogService logService) {
        this.logService = logService;
    }

    // Save a new daily log entry
    @PostMapping
    public ResponseEntity<?> saveLog(@RequestBody HealthLog log, Authentication authentication) {
        try {
            String email = authentication.getName();
            HealthLog savedLog = logService.saveLog(email, log);

            return ResponseEntity.ok(Map.of(
                "message", "Daily entry saved successfully!",
                "log", savedLog
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Get all logs for the logged-in user
    @GetMapping
    public ResponseEntity<?> getLogs(Authentication authentication) {
        try {
            String email = authentication.getName();
            List<HealthLog> logs = logService.getLogsByEmail(email);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}