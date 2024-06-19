package org.wsrd.car_rental_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.wsrd.car_rental_system.enums.UserRole;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
