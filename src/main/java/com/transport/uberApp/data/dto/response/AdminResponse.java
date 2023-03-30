package com.transport.uberApp.data.dto.response;

import com.transport.uberApp.data.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminResponse {
    private Long employeeId;
    private AppUser userDetails;
}
