package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
}
