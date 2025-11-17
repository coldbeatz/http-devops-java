package api.user;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Сутність користувача.
 * Відповідає таблиці users у базі даних.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    /**
     * Первинний ключ користувача.
     * Генерується автоматично.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ім'я користувача.
     * Не може бути null, максимальна довжина — 100 символів.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Email користувача.
     * Не може бути null, має бути унікальним, максимальна довжина — 150 символів.
     */
    @Column(nullable = false, unique = true, length = 150)
    private String email;
}
