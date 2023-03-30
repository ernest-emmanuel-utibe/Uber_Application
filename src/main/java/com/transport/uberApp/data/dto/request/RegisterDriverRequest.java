package com.transport.uberApp.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import static com.transport.uberApp.util.AppUtilities.EMAIL_REGEX_STRING;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDriverRequest {
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String name;

    @NotNull(message = "field email cannot be null")
    @NotEmpty(message = "field email cannot be empty")
    @Email(message = "must be valid email address", regexp = EMAIL_REGEX_STRING)
    private String email;

    @Size(min = 8, max = 20)
    @NotEmpty
    @NotNull
    private String password;
    @NotNull(message = "please upload license image")
    private MultipartFile licenseImage;
}
