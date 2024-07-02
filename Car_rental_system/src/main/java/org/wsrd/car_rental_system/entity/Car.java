package org.wsrd.car_rental_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String transmission;
    private String type;
    private String description;
    private Long price;
    private Date year;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}
