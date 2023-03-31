package com.transport.uberApp.mapper;

import com.transport.uberApp.data.dto.request.RegisterPassengerRequest;
import com.transport.uberApp.data.models.AppUser;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParaMapper {

    public static AppUser map(RegisterPassengerRequest request){
        AppUser appUser = new AppUser();
        appUser.setName(request.getName());
        appUser.setPassword(request.getPassword());
        appUser.setEmail(request.getEmail());
        return appUser;
    }
}
