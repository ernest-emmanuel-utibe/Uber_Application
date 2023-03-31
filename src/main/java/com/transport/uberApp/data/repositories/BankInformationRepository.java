package com.transport.uberApp.data.repositories;

import com.transport.uberApp.data.models.BankInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankInformationRepository extends JpaRepository<BankInformation, Long> {
}
