package ru.job4j.cars.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "car_color")
@Data
public class CarColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
