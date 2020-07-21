package com.example.splitwize.splitwize.service.Impl;

import com.example.splitwize.splitwize.classes.CustomUserDetails;
import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.enums.UserRole;
import com.example.splitwize.splitwize.exception.UserExistsException;
import com.example.splitwize.splitwize.exception.UserNotFoundException;
import com.example.splitwize.splitwize.repository.UserRepository;
import com.example.splitwize.splitwize.request.AuthRequest;
import com.example.splitwize.splitwize.request.UserRegisterRequest;
import com.example.splitwize.splitwize.response.AuthResponse;
import com.example.splitwize.splitwize.response.UserDetailsResponse;
import com.example.splitwize.splitwize.service.CustomUserDetailsService;
import com.example.splitwize.splitwize.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        return user.map(CustomUserDetails::new).get();
    }

    @Override
    public AuthResponse<CustomUserDetails> login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect credentials:" + e.getMessage());
        }
        final CustomUserDetails customUserDetails = loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(customUserDetails);

        AuthResponse autResponse = new AuthResponse<CustomUserDetails>(jwt);
        autResponse.setData(customUserDetails);
        return autResponse;
    }

    @Override
    public User addUser(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByUsername(userRegisterRequest.getUsername()).isPresent()) {
            throw new UserExistsException("User " + userRegisterRequest.getUsername() + " already exists");
        }
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
        user.setEmail(userRegisterRequest.getEmail());
        user.setRoles(UserRole.ROLE_USER.toString());
        user.setActive(true);
        return userRepository.save(user);
    }


    @Override
    public UserDetailsResponse getUserDetailsFromId(int id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
        return user.map(UserDetailsResponse::new).get();
    }

}