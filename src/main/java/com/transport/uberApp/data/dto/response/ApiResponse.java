package com.transport.uberApp.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ApiResponse {
    private String message;
    private BigDecimal fare;
    private String estimatedTimeOfArrival;
}
