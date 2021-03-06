package com.tcc.sospets.services.classes;

import com.tcc.sospets.business.models.entities.User;
import com.tcc.sospets.business.repositories.IUserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    IUserRepositorio userRepositorio;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepositorio.findByEmail(email).orElseThrow();

    }
}

