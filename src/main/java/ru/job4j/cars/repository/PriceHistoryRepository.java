package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;

import java.util.List;

@ThreadSafe
@Repository
@AllArgsConstructor
public class PriceHistoryRepository {
    private final CrudRepository crudRepository;

    public List<PriceHistory> findAll() {
        return crudRepository.query("from PriceHistory", PriceHistory.class);
    }

    public boolean save(PriceHistory priceHistory) {
        return crudRepository.run(session -> session.persist(priceHistory));
    }

    public boolean update(PriceHistory priceHistory) {
        return crudRepository.run(session -> session.merge(priceHistory));
    }
}
