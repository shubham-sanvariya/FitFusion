package com.CN.FitFusion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.JwtRequest;
import com.CN.FitFusion.dto.JwtResponse;
import com.CN.FitFusion.jwt.JwtAuthenticationHelper;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtAuthenticationHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtResponse login(JwtRequest request){

        this.doAuthenticate(request.getUsername(),request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtHelper.generateToken(userDetails);

        return JwtResponse.builder().jwtToken(token).build();
    }
    
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
