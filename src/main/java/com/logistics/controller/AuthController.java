package com.logistics.controller;

import com.logistics.DTO.AuthRequest;
import com.logistics.DTO.UserRegistration;
import com.logistics.entity.User;
import com.logistics.repository.UserRepository;
import com.logistics.service.CustomUserDetailsService;
import com.logistics.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try{
            System.out.println(request + " " +  request.getUsername());
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            System.out.println("authentication" + auth);
            // Return token (JWT) here later
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok( token);
        }
        catch (Exception e){
            System.out.println("Exception"  + e);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid Username or Password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistration request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already in use.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


}

