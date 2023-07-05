package ru.job4j.cars.model;


import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "brand")
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
