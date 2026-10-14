package com.healthleans.service;

import com.healthleans.model.HealthLog;
import com.healthleans.model.User;
import com.healthleans.repository.HealthLogRepository;
import com.healthleans.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthLogService {

    private final HealthLogRepository logRepository;
    private final UserRepository userRepository;

    public HealthLogService(HealthLogRepository logRepository, UserRepository userRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
    }

    // Save a new daily log
    public HealthLog saveLog(String email, HealthLog log) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        log.setUser(user);
        return logRepository.save(log);
    }

    // Get all logs for a user (newest first)
    public List<HealthLog> getLogsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return logRepository.findByUserOrderByDateDesc(user);
    }
}