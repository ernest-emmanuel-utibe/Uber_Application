package com.transport.uberApp.config.security.services;

import com.transport.uberApp.config.security.users.SecureUser;
import com.transport.uberApp.data.models.AppUser;
import com.transport.uberApp.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    
    private final AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserService.getByEmail(username);
        return new SecureUser(user);
    }
}
