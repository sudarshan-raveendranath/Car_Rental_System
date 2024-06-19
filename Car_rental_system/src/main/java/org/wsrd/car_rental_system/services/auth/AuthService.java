package org.wsrd.car_rental_system.services.auth;

import org.wsrd.car_rental_system.dto.SignupRequest;
import org.wsrd.car_rental_system.dto.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
