package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.History;
import ru.job4j.cars.repository.HistoryRepository;

import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public boolean save(History history) {
        return historyRepository.save(history);
    }
}
