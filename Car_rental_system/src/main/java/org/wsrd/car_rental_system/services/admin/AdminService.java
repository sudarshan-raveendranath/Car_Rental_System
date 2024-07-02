package org.wsrd.car_rental_system.services.admin;

import org.wsrd.car_rental_system.dto.CarDto;

import java.io.IOException;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;
}
