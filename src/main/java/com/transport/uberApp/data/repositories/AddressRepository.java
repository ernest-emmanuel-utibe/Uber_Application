package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
