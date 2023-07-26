package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import java.util.*;

@ThreadSafe
@Repository
@AllArgsConstructor
public class UserRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public Optional<User> create(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public boolean update(User user) {
        return crudRepository.run(session -> session.merge(user));
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public boolean delete(int userId) {
        return crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        return crudRepository.query("from User order by id asc", User.class);
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User as i where i.login = :fLogin and i.password = :fPassword",
                User.class, Map.of("fLogin", login, "fPassword", password));
    }
}
