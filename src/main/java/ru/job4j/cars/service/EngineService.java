package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class EngineService {

    private final EngineRepository engineRepository;

    public List<Engine> findAll() {
        return engineRepository.findAll();
    }

    public boolean save(Engine engine) {
        return engineRepository.save(engine);
    }

    public boolean update(Engine engine) {
        return engineRepository.update(engine);
    }
}
