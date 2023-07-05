package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CarRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public boolean save(Car car) {
        return carRepository.save(car);
    }

    public boolean update(Car car) {
        return carRepository.update(car);
    }
}
