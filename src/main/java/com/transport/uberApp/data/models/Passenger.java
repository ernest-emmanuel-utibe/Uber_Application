package com.transport.uberApp.data.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @OneToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JsonUnwrapped
    private AppUser userDetails;
}
