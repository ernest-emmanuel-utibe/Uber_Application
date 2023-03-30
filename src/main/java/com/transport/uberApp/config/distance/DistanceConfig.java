package com.transport.uberApp.config.distance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DistanceConfig {
    private String googleDistanceUrl;
    private String googleApiKey;
}
