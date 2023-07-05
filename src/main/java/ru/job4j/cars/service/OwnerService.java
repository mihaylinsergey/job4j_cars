package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.OwnerRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public boolean save(Owner owner) {
        return ownerRepository.save(owner);
    }
}
