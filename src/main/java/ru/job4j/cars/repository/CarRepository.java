package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import java.util.List;

@Repository
@AllArgsConstructor
public class CarRepository {

    private final CrudRepository crudRepository;

    public List<Car> findAll() {
       return crudRepository.query("from Car", Car.class);
    }

    public boolean save(Car car) {
        return crudRepository.run(session -> session.persist(car));
    }

    public boolean update(Car car) {
        return crudRepository.run(session -> session.merge(car));
    }
}
