package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.History;
import java.util.List;

@ThreadSafe
@Repository
@AllArgsConstructor
public class HistoryRepository {
    private final CrudRepository crudRepository;

    public List<History> findAll() {
        return crudRepository.query("from History", History.class);
    }

    public boolean save(History history) {
        return crudRepository.run(session -> session.persist(history));
    }
}
