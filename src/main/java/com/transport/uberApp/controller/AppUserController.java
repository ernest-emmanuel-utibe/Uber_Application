package com.transport.uberApp.controller;

import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.exception.BusinessLogicException;
import com.transport.uberApp.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService userService;


    @PostMapping(value = "/upload/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfileImage(@RequestParam(value = "file") MultipartFile file, @PathVariable Long userId){
        try {
            ApiResponse response = userService.uploadProfileImage(file, userId);
            return ResponseEntity.ok(response);
        }catch (BusinessLogicException exception){
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(exception.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/account/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam Long userId, @RequestParam String token){
        try{
            var response = userService.verifyAccount(userId, token);
            return ResponseEntity.ok(response);
        }catch (BusinessLogicException exception){
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(exception.getMessage())
                            .build()
            );
        }
    }
}
