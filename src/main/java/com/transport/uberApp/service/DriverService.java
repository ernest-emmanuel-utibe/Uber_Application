package com.transport.uberApp.service;

import com.transport.uberApp.data.dto.request.RegisterDriverRequest;
import com.transport.uberApp.data.dto.response.RegisterResponse;
import com.transport.uberApp.data.models.Driver;

import java.util.Optional;

public interface DriverService {
    RegisterResponse register(RegisterDriverRequest request);
    Optional<Driver> getDriverBy(Long driverId);

    void saveDriver(Driver driver);
}
