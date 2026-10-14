package com.healthleans.controller;

import com.healthleans.model.HealthProfile;
import com.healthleans.service.HealthProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final HealthProfileService profileService;

    public ProfileController(HealthProfileService profileService) {
        this.profileService = profileService;
    }

    // Save or Update Health Profile
    @PostMapping
    public ResponseEntity<?> saveProfile(@RequestBody HealthProfile profile, Authentication authentication) {
        try {
            String email = authentication.getName();   // Get logged-in user's email from JWT
            HealthProfile saved = profileService.saveProfile(email, profile);

            return ResponseEntity.ok(Map.of(
                "message", "Health profile saved successfully!",
                "profile", saved
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Get user's health profile
    @GetMapping
    public ResponseEntity<?> getProfile(Authentication authentication) {
        try {
            String email = authentication.getName();
            return profileService.getProfileByEmail(email)
                    .map(profile -> ResponseEntity.ok(profile))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}