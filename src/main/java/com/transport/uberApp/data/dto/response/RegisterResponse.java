package com.transport.uberApp.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponse {
    private Long id;
    private String message;
    private boolean isSuccess;
}
