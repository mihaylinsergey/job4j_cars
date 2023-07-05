package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.repository.CarModelRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;

    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }

    public boolean save(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public boolean update(CarModel carModel) {
        return carModelRepository.update(carModel);
    }
}
