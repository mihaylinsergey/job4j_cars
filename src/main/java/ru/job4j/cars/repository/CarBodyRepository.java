package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBody;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class CarBodyRepository {

    private final CrudRepository crudRepository;

    public List<CarBody> findAll() {
        return crudRepository.query("from CarBody", CarBody.class);
    }

    public boolean save(CarBody carBody) {
        return crudRepository.run(session -> session.persist(carBody));
    }

    public Optional<CarBody> findById(int id) {
        return crudRepository.optional("""
            from CarBody i 
            where i.id = :fId
            """, CarBody.class, Map.of("fId", id));
    }
}
