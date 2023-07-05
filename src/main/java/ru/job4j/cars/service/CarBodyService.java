package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarBody;
import ru.job4j.cars.repository.CarBodyRepository;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
@AllArgsConstructor
public class CarBodyService {

    private final CarBodyRepository carBodyRepository;

    public List<CarBody> findAll() {
        return carBodyRepository.findAll();
    }

    public boolean save(CarBody carBody) {
        return carBodyRepository.save(carBody);
    }

    public Optional<CarBody> findById(int id) {
        return carBodyRepository.findById(id);
    }
}
