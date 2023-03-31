package com.transport.uberApp.data.models;

import com.transport.uberApp.data.dto.request.LocationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Location startingPoint;
    @OneToOne(cascade = CascadeType.ALL)
    private Location destinationPoint;
    @OneToOne(cascade = CascadeType.ALL)
    private Passenger passenger;
    private String eta;
    private BigDecimal fare;
    private LocalDateTime localDateTime;

}
