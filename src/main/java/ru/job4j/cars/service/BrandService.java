package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.BrandRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> findAll() {
       return brandRepository.findAll();
    }

    public boolean save(Brand brand) {
        return brandRepository.save(brand);
    }

    public boolean update(Brand brand) {
        return brandRepository.update(brand);
    }
}
