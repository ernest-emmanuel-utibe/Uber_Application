package com.transport.uberApp.service.impl;

import com.transport.uberApp.cloud.CloudService;
import com.transport.uberApp.data.dto.request.EmailNotificationRequest;
import com.transport.uberApp.data.dto.request.Recipient;
import com.transport.uberApp.data.dto.request.RegisterDriverRequest;
import com.transport.uberApp.data.dto.response.RegisterResponse;
import com.transport.uberApp.data.models.AppUser;
import com.transport.uberApp.data.models.Driver;
import com.transport.uberApp.data.models.Role;
import com.transport.uberApp.data.repositories.DriverRepository;
import com.transport.uberApp.exception.ImageUploadException;
import com.transport.uberApp.notification.MailService;
import com.transport.uberApp.service.DriverService;
import com.transport.uberApp.util.AppUtilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final CloudService cloudService;
    private final ModelMapper modelMapper;

    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterDriverRequest request) {
        AppUser driverDetails = modelMapper.map(request, AppUser.class);
        driverDetails.setPassword(passwordEncoder.encode(request.getPassword()));
        driverDetails.setCreatedAt(LocalDateTime.now().toString());
        driverDetails.setRoles(new HashSet<>());
        driverDetails.getRoles().add(Role.DRIVER);
        //step
        //1. upload drivers license image

        var imageUrl = cloudService.upload(request.getLicenseImage());
        if (imageUrl==null)
            throw new ImageUploadException("Driver Registration failed");
        // step
        //2. create driver object
        Driver driver = Driver.builder()
                .userDetails(driverDetails)
                .licenseImage(imageUrl)
                .build();
        //step
        //3. save driver
        Driver savedDriver = driverRepository.save(driver);
        
        //4. send verification mail to driver
        EmailNotificationRequest emailRequest = buildNotificationRequest(savedDriver.getUserDetails().getEmail(), savedDriver.getUserDetails().getName(), driver.getId());
        String response = mailService.sendHtmlMail(emailRequest);
        if (response==null) return getRegisterFailureResponse();
        return RegisterResponse.builder()
                .id(savedDriver.getId())
                .isSuccess(true)
                .message("Driver Registration Successful")
                .build();
    }

    private static RegisterResponse getRegisterFailureResponse() {
        return RegisterResponse.builder()
                .id(-1L)
                .isSuccess(false)
                .message("Driver Registration Failed")
                .build();
    }


    private EmailNotificationRequest buildNotificationRequest(String email, String name, Long userId) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        request.getTo().add(new Recipient(name, email));
        String template = AppUtilities.getMailTemplate();
        String content = String.format(template, name, AppUtilities.generateVerificationLink(userId));
        request.setHtmlContent(content);
        return request;
    }



    @Override
    public Optional<Driver> getDriverBy(Long driverId) {
        return driverRepository.findById(driverId);
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }
}
