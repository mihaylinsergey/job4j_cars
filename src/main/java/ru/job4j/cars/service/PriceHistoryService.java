package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.PriceHistoryRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;

    public List<PriceHistory> findAll() {
        return priceHistoryRepository.findAll();
    }

    public boolean save(PriceHistory priceHistory) {
        return priceHistoryRepository.save(priceHistory);
    }

    public boolean update(PriceHistory priceHistory) {
        return priceHistoryRepository.update(priceHistory);
    }
}
