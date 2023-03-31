package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
