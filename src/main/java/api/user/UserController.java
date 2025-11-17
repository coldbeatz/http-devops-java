package api.user;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * REST-контролер для роботи з користувачами.
 * <p>
 * Обробляє HTTP-запити за шляхом "/api/users" і використовує {@link UserRepository} для взаємодії з базою даних.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Репозиторій для доступу до даних користувачів.
     */
    private final UserRepository repository;

    /**
     * Інжекція репозиторію через конструктор (рекомендований підхід у Spring).
     *
     * @param repository репозиторій користувачів
     */
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Отримати список усіх користувачів.
     * <p>
     * GET /api/users
     *
     * @return список усіх користувачів з бази даних
     */
    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * Отримати користувача за його id.
     * <p>
     * GET /api/users/{id}
     *
     * @param id ідентифікатор користувача
     *
     * @return знайдений користувач
     *
     * @throws ResponseStatusException якщо користувача не знайдено (HTTP 404)
     */
    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User with id " + id + " not found")
                );
    }

    /**
     * Створити нового користувача.
     * <p>
     * POST /api/users
     *
     * @param user дані користувача, що приходять у тілі запиту (JSON -> User)
     *
     * @return створений користувач з присвоєним id
     *
     * @throws ResponseStatusException якщо користувач з таким email вже існує (HTTP 409)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User with email " + user.getEmail() + " already exists"
            );
        }

        return repository.save(user);
    }

    /**
     * Оновити дані існуючого користувача.
     * <p>
     * PUT /api/users/{id}
     * <p>
     * Повністю замінює name та email користувача на нові значення з тіла запиту.
     *
     * @param id      ідентифікатор користувача, якого оновлюємо
     * @param updated об'єкт з оновленими полями (name, email)
     *
     * @return оновлений користувач
     *
     * @throws ResponseStatusException якщо користувача з таким id не знайдено (HTTP 404)
     */
    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User updated) {
        User existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User with id " + id + " not found"
                ));

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());

        return repository.save(existing);
    }

    /**
     * Видалити користувача за id.
     * <p>
     * DELETE /api/users/{id}
     *
     * @param id ідентифікатор користувача, якого потрібно видалити
     *
     * @return об'єкт з текстовим повідомленням про успішне видалення
     *
     * @throws ResponseStatusException якщо користувача з таким id не існує (HTTP 404)
     */
    @DeleteMapping("/{id}")
    public ApiMessage delete(@PathVariable("id") Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }

        repository.deleteById(id);

        return new ApiMessage("User with id " + id + " deleted successfully");
    }
}
