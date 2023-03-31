package com.transport.uberApp.data.dto.response;

import com.transport.uberApp.data.models.Location;
import com.transport.uberApp.data.models.Passenger;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RideDto {
    private Long id;

    private Passenger passenger;

    private Location origin;

    private Location destination;

    private String eta;

    private BigDecimal fare;

    private final LocalDateTime createdAt = LocalDateTime.now();
}
