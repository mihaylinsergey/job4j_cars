package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Category;
import ru.job4j.cars.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public boolean save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }
}
