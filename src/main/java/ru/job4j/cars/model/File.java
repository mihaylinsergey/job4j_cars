package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "file")
@Data
public class File {

    private int id;

    private String name;

    private String path;
}
