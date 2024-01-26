package com.befriend.befriend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.befriend.befriend.dto.JwtResponse;
import com.befriend.befriend.model.User;
import com.befriend.befriend.model.UserRepository;
import com.befriend.befriend.security.LoginRequest;
import com.befriend.befriend.security.SignupRequest;
import com.befriend.befriend.utils.JwtUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    // include any other services or components you need, like password encryption

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity<>("Error: Email is already in use!", HttpStatus.BAD_REQUEST);
    }

    // Create new user's account
    User user = new User(signUpRequest.getName(), 
                         signUpRequest.getEmail(), 
                         passwordEncoder.encode(signUpRequest.getPassword()));

    userRepository.save(user);

    Map<String, String> response = new HashMap<>();
    return ResponseEntity.ok(response);
        
    }

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(passwordEncoder.matches(loginRequest.getPassword(), userDetailsService.loadUserByUsername(loginRequest.getName()).toString()));
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtUtils.generateJwtToken(authentication); 

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}

