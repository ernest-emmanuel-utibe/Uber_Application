package com.transport.uberApp.data.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneNumber;
    @OneToOne
    private Address address;
    private Gender gender;
    private String licenseId;
    private String licenseImage;
    private int age;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Referee referee;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BankInformation bankInformation;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AppUser userDetails;
    private AcceptRide acceptRide;
}
