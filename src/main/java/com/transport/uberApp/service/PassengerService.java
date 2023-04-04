package com.transport.uberApp.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.transport.uberApp.data.dto.request.BookRideRequest;
import com.transport.uberApp.data.dto.request.RegisterPassengerRequest;
import com.transport.uberApp.data.dto.response.ApiResponse;
import com.transport.uberApp.data.dto.response.RegisterResponse;
import com.transport.uberApp.data.models.Passenger;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PassengerService {
    RegisterResponse register(RegisterPassengerRequest registerRequest);
    Passenger getPassengerById(Long passengerId);
    void savePassenger(Passenger passenger);
    Optional<Passenger> getPassengerBy(Long passengerId);
    Passenger updatePassenger(Long passengerId, JsonPatch updatePayload);
    Page<Passenger> getAllPassenger(int pageNumber);
    void deletePassenger(Long id);
    ApiResponse bookRide(BookRideRequest bookRideRequest);
    Passenger getCurrentPassenger();
}
