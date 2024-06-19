package org.wsrd.car_rental_system.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String name;
    private String  password;
}
