package com.transport.uberApp.service.impl;

import com.transport.uberApp.service.AppUserService;
import com.transport.uberApp.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final PassengerService passengerService;
    private final DriverService driverService;
    private final CloudService cloudService;

    private final AppUserRepository appUserRepository;

    @Override
    public ApiResponse uploadProfileImage(MultipartFile profileImage, Long userId) {
        Optional<Driver> foundDriver = Optional.empty();
        Optional<Passenger> foundPassenger;
        foundPassenger = findPassenger(userId);
        if (foundPassenger.isEmpty()) foundDriver = findDriver(userId);
        if (foundPassenger.isEmpty()&&foundDriver.isEmpty()) throw new UserNotFoundException(
                String.format(USER_WITH_ID_NOT_FOUND.getMessage(), userId)
        );
        String imageUrl = cloudService.upload(profileImage);
        foundPassenger.ifPresent(passenger -> updatePassengerProfileImage(imageUrl, passenger));
        foundDriver.ifPresent(driver -> updateDriverProfileImage(imageUrl, driver));

        return ApiResponse.builder()
                .message("SUCCESS")
                .build();
    }

    private Optional<Driver> findDriver(Long userId) {
        return driverService.getDriverBy(userId);
    }

    private Optional<Passenger> findPassenger(Long userId) {
        return passengerService.getPassengerBy(userId);
    }

    @Override
    public ApiResponse verifyAccount(Long userId, String token) {
        if (AppUtilities.isValidToken(token)) return getVerifiedResponse(userId);
        throw new BusinessLogicException(
                String.format("account verification for user with %d failed", userId)
        );
    }


    private ApiResponse getVerifiedResponse(Long userId) {
        Optional<Passenger> foundPassenger;
        Optional<Driver> foundDriver=Optional.empty();

        foundPassenger = findPassenger(userId);
        if (foundPassenger.isEmpty()) foundDriver = findDriver(userId);
        if (foundDriver.isEmpty()&&foundPassenger.isEmpty()) throw new UserNotFoundException(
                String.format(USER_WITH_ID_NOT_FOUND.getMessage(), userId)
        );
        foundDriver.ifPresent(driver -> enableDriverAccount(driver));
        foundPassenger.ifPresent(passenger -> enablePassengerAccount(passenger));
        return ApiResponse.builder()
                .message("success")
                .build();
    }

    private void enablePassengerAccount(Passenger passenger) {
        passenger.getUserDetails().setIsEnabled(true);
        passengerService.savePassenger(passenger);
    }

    private void enableDriverAccount(Driver driver) {
        driver.getUserDetails().setIsEnabled(true);
        driverService.saveDriver(driver);
    }

    private void updateDriverProfileImage(String imageUrl, Driver driver) {
        driver.getUserDetails().setProfileImage(imageUrl);
        driverService.saveDriver(driver);
    }

    private void updatePassengerProfileImage(String imageUrl, Passenger passenger) {
        passenger.getUserDetails().setProfileImage(imageUrl);
        passengerService.savePassenger(passenger);
    }




    @Override
    public AppUser getByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("user with email not found"));
    }
}
