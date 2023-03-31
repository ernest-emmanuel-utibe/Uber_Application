package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
