package org.wsrd.car_rental_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wsrd.car_rental_system.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
