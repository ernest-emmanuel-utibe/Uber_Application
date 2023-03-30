package com.transport.uberApp.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InvitedAdminRequest {
    private String email;
    private String name;
}
