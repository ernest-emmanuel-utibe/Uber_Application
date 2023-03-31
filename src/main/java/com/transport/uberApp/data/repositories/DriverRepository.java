package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}
