package org.wsrd.car_rental_system.dto;

import lombok.Data;
import org.wsrd.car_rental_system.enums.UserRole;

@Data
public class AuthenticationResponse {

    private String jwt;
    private UserRole userRole;
    private Long userId;
}
