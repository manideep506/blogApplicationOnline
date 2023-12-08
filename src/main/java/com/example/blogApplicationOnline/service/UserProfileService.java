package com.example.blogApplicationOnline.service;

import com.example.blogApplicationOnline.model.UserProfile;
import com.example.blogApplicationOnline.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile getUserProfileByUsername(String username) {
        return userProfileRepository.findByUsername(username);
    }

    public UserProfile createUserProfile(UserProfile userProfile) {

        return userProfileRepository.save(userProfile);
    }
}
