package org.wsrd.car_rental_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.wsrd.car_rental_system.dto.AuthenticationRequest;
import org.wsrd.car_rental_system.dto.AuthenticationResponse;
import org.wsrd.car_rental_system.dto.SignupRequest;
import org.wsrd.car_rental_system.dto.UserDto;
import org.wsrd.car_rental_system.entity.User;
import org.wsrd.car_rental_system.repository.UserRepository;
import org.wsrd.car_rental_system.services.auth.AuthService;
import org.wsrd.car_rental_system.services.jwt.UserService;
import org.wsrd.car_rental_system.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        if (authService.hasCustomerWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("User already exist with this email",HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = authService.createCustomer(signupRequest);
        if (createdCustomerDto == null) {
            return new ResponseEntity<>("Customer not created, come again later", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        logger.info("Attempting to authenticate user: {}", authenticationRequest.getEmail());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
            logger.info("Authentication successful for user: {}", authenticationRequest.getEmail());
        } catch (BadCredentialsException e) {
            logger.error("Authentication failed for user: {}", authenticationRequest.getEmail(), e);
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        logger.debug("Loaded user details for user: {}", authenticationRequest.getEmail());

        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        logger.debug("Generated JWT for user: {}", userDetails.getUsername());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            logger.info("Authentication response created for user: {}", userDetails.getUsername());
        }
        return authenticationResponse;
    }
}
