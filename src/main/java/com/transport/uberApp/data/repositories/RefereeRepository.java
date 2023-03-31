package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefereeRepository extends JpaRepository<Referee, Long> {
}
