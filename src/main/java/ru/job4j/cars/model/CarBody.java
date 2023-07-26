package ru.job4j.cars.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "car_body")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
