package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarColor;
import java.util.List;

@ThreadSafe
@Repository
@AllArgsConstructor
public class CarColorRepository {
    private final CrudRepository crudRepository;

    public List<CarColor> findAll() {
        return crudRepository.query("from CarColor", CarColor.class);
    }

    public boolean save(CarColor carColor) {
        return crudRepository.run(session -> session.persist(carColor));
    }

    public boolean update(CarColor carColor) {
        return crudRepository.run(session -> session.merge(carColor));
    }
}
