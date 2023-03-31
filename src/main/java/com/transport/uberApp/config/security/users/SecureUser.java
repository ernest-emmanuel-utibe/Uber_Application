package com.transport.uberApp.config.security.users;

import com.transport.uberApp.data.models.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecureUser implements UserDetails {
    private final AppUser appUser;
    @Override
    public String getUsername() {
        return appUser.getEmail();
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//
//        for (Role role:appUser.getRoles()) {
//            GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
//            authorities.add(authority);
//
//        }
//        return authorities;

        return appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
