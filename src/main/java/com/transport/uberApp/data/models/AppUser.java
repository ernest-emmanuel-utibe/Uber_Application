package com.transport.uberApp.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AppUser {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;

    @Column(unique = true)
    private String email;
    private String profileImage;
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private String createdAt;
    private Boolean isEnabled;
}
