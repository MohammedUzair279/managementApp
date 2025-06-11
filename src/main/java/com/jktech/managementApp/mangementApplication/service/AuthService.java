package com.jktech.managementApp.mangementApplication.service;

import com.jktech.managementApp.mangementApplication.dto.AuthRequest;
import com.jktech.managementApp.mangementApplication.dto.AuthResponse;
import com.jktech.managementApp.mangementApplication.dto.RegisterRequest;
import com.jktech.managementApp.mangementApplication.model.User;
import com.jktech.managementApp.mangementApplication.repository.UserRepository;
import com.jktech.managementApp.mangementApplication.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public String register(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRoles(registerRequest.getRoles());
        userRepository.save(user);
        return "User Registered Successfully";
    }

    public AuthResponse authenticate(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        var user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();
        var jwt = jwtUtil.generateToken(user);
        return new AuthResponse(jwt);
    }
}
