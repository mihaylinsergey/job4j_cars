package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import java.util.List;

@ThreadSafe
@Repository
@AllArgsConstructor
public class EngineRepository {

    private final CrudRepository crudRepository;

    public List<Engine> findAll() {
        return crudRepository.query("from Engine", Engine.class);
    }

    public boolean save(Engine engine) {
        return crudRepository.run(session -> session.persist(engine));
    }

    public boolean update(Engine engine) {
        return crudRepository.run(session -> session.merge(engine));
    }
}
