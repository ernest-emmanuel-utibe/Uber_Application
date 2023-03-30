package com.transport.uberApp.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DistanceMatrixElement {
    private DistanceMatrixElementStatus status;

    private GoogleDistance distance;

    private GoogleDuration duration;
    private Fare fare;
}
