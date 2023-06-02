package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Owner owner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
    name = "history_owners",
    joinColumns = {@JoinColumn(name = "car_id")},
    inverseJoinColumns = {@JoinColumn(name = "owner_id")}
    )
    private Set<Owner> owners = new HashSet<>();
}
