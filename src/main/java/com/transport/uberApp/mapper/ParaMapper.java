package com.transport.uberApp.mapper;

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
