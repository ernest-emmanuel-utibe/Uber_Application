package com.transport.uberApp.service;

import java.util.Optional;

public interface DriverService {
    RegisterResponse register(RegisterDriverRequest request);
    Optional<Driver> getDriverBy(Long driverId);

    void saveDriver(Driver driver);
}
