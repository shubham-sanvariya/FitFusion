package com.CN.FitFusion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager manager;
    
    public void doAuthenticate(String username, String password){
        UsernamePasswordAuthenticationToken upat = 
            new UsernamePasswordAuthenticationToken(username,password);
        try {
            manager.authenticate(upat);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
