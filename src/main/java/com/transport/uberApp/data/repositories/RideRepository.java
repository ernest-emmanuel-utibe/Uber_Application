package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.Passenger;
import com.transport.uberApp.data.models.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findAllByPassenger(Passenger passenger, Pageable pageable);
}
