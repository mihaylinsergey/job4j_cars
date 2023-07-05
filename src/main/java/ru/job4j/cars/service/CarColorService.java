package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarColor;
import ru.job4j.cars.repository.CarColorRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class CarColorService {

    private final CarColorRepository carColorRepository;

    public List<CarColor> findAll() {
        return carColorRepository.findAll();
    }

    public boolean save(CarColor carColor) {
        return carColorRepository.save(carColor);
    }

    public boolean update(CarColor carColor) {
        return carColorRepository.update(carColor);
    }
}
