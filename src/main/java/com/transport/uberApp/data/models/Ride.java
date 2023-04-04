package com.transport.uberApp.data.models;

import com.transport.uberApp.data.dto.request.LocationDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.ALL)
    private Location origin;

    @OneToOne(cascade = CascadeType.ALL)
    private Location destination;

    private String eta;

    private BigDecimal fare;

    private final LocalDateTime createdAt = LocalDateTime.now();

}
