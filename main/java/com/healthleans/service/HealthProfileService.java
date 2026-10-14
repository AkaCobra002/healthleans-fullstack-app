package com.healthleans.service;

import com.healthleans.model.HealthProfile;
import com.healthleans.model.User;
import com.healthleans.repository.HealthProfileRepository;
import com.healthleans.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthProfileService {

  private final HealthProfileRepository profileRepository;
  private final UserRepository userRepository;

  public HealthProfileService(HealthProfileRepository profileRepository, UserRepository userRepository){
    this.profileRepository = profileRepository;
    this.userRepository = userRepository;
  }

  //save or Update Profile
  public HealthProfile saveProfile(String email, HealthProfile profile) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<HealthProfile> existing = profileRepository.findByUser(user);

        if (existing.isPresent()) {
            // Update existing profile
            HealthProfile existingProfile = existing.get();
            existingProfile.setDiabetes(profile.isDiabetes());
            existingProfile.setDiabetesType(profile.getDiabetesType());
            existingProfile.setObesity(profile.isObesity());
            existingProfile.setObesityClass(profile.getObesityClass());
            existingProfile.setWeightGoal(profile.getWeightGoal());
            existingProfile.setSurgery(profile.isSurgery());
            existingProfile.setSurgeryType(profile.getSurgeryType());
            existingProfile.setOther(profile.isOther());
            existingProfile.setOtherDescription(profile.getOtherDescription());
            return profileRepository.save(existingProfile);
        } else {
            // Create new profile
            profile.setUser(user);
            return profileRepository.save(profile);
        }
    }

    //Get profile by email
    public Optional<HealthProfile> getProfileByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return Optional.empty();
        return profileRepository.findByUser(user);
    }
  
}
