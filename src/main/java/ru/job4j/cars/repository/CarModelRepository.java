package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<CarModel> findById(int id) {
        return crudRepository.optional("""
            from CarModel i 
            where i.id = :fId
            """, CarModel.class, Map.of("fId", id));
    }
}
