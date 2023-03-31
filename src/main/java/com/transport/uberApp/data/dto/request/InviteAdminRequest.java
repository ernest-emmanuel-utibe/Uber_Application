package com.transport.uberApp.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InviteAdminRequest {
    private String email;
    private String name;
}
