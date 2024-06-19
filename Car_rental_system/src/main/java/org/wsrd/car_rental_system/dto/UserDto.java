package org.wsrd.car_rental_system.dto;

import lombok.Data;
import org.wsrd.car_rental_system.enums.UserRole;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
