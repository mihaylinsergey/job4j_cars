package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;
import java.util.List;

@ThreadSafe
@Repository
@AllArgsConstructor
public class CarModelRepository {

    private final CrudRepository crudRepository;

    public List<CarModel> findAll() {
        return crudRepository.query("from CarModel", CarModel.class);
    }

    public boolean save(CarModel carModel) {
        return crudRepository.run(session -> session.persist(carModel));
    }

    public boolean update(CarModel carModel) {
        return crudRepository.run(session -> session.merge(carModel));
    }
}
