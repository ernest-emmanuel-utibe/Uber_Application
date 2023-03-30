package com.transport.uberApp.service;

import org.springframework.web.multipart.MultipartFile;

public interface AppUserService {
    ApiResponse uploadProfileImage(MultipartFile profileImage, Long userId);

    ApiResponse verifyAccount(Long userId, String token);


    AppUser getByEmail(String email);
}
