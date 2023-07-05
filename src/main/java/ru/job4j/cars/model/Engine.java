package ru.job4j.cars.model;


import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "engine")
@Data
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double volume;

    @Column(name = "power_engine")
    private int power;


}