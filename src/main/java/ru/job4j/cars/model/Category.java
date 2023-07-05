package ru.job4j.cars.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
