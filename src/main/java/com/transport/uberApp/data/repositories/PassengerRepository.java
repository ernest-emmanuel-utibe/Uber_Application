package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findPassengerByUserDetails_Email(String userDetails_email);

}
