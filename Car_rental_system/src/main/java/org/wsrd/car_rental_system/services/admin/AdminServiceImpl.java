package org.wsrd.car_rental_system.services.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wsrd.car_rental_system.dto.CarDto;
import org.wsrd.car_rental_system.entity.Car;
import org.wsrd.car_rental_system.repository.CarRepository;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
        try {
            Car car = new Car();
            car.setBrand(carDto.getBrand());
            car.setColor(carDto.getColor());
            car.setName(carDto.getName());
            car.setTransmission(carDto.getTransmission());
            car.setType(carDto.getType());
            car.setDescription(carDto.getDescription());
            car.setPrice(carDto.getPrice());
            car.setYear(carDto.getYear());
            car.setImage(carDto.getImage().getBytes());
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            return false;
        }



    }
}
