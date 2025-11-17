package api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторій для роботи з сутністю {@link User}.
 * <p>
 * Наслідується від {@link JpaRepository}, тому автоматично отримує
 * стандартні CRUD-операції (збереження, пошук, видалення тощо).
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Знаходить користувача за email.
     *
     * @param email email користувача, якого шукаємо
     *
     * @return {@link Optional} з користувачем, якщо знайдено,
     *         або {@link Optional#empty()} якщо користувача з таким email немає
     */
    Optional<User> findByEmail(String email);

    /**
     * Перевіряє, чи існує користувач з вказаним email.
     *
     * @param email email користувача
     *
     * @return {@code true}, якщо користувач з таким email вже є в БД,
     *         {@code false} — якщо немає
     */
    boolean existsByEmail(String email);
}
