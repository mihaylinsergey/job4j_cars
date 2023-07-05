package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepository;
import java.util.*;

@ThreadSafe
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(int userId) {
        userRepository.delete(userId);
    }

    public List<User> findAllOrderById() {
        return userRepository.findAllOrderById();
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public List<User> findByLikeLogin(String key) {
        return userRepository.findByLikeLogin(key);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}
