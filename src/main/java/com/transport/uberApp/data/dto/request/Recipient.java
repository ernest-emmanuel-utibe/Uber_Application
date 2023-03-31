package com.transport.uberApp.data.dto.request;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Recipient {
    private String name;
    private String email;
}
