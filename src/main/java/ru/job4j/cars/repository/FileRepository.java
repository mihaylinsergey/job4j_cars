package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;
import java.util.List;
import java.util.Map;

@ThreadSafe
@Repository
@AllArgsConstructor
public class FileRepository {

    private final CrudRepository crudRepository;

    public List<File> findAll() {
        return crudRepository.query("from File", File.class);
    }

    public File save(File file) {
        crudRepository.run(session -> session.persist(file));
        return file;
    }

    public List<File> findById(int id) {
        return crudRepository.query("from File as i where i.id = :fId",
                File.class, Map.of("fId", id));
    }
}
