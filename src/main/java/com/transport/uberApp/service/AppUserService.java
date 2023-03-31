package com.transport.uberApp.service;

import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.data.models.AppUser;
import org.springframework.web.multipart.MultipartFile;

public interface AppUserService {
    ApiResponse uploadProfileImage(MultipartFile profileImage, Long userId);

    ApiResponse verifyAccount(Long userId, String token);


    AppUser getByEmail(String email);
}
