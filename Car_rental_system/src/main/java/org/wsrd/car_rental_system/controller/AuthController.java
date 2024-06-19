package org.wsrd.car_rental_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wsrd.car_rental_system.dto.SignupRequest;
import org.wsrd.car_rental_system.dto.UserDto;
import org.wsrd.car_rental_system.services.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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
}
