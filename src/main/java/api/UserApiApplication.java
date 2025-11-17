package api;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Головний клас Spring Boot-застосунку.
 * <p>
 * Анотація {@link SpringBootApplication} вмикає:
 * <ul>
 *     <li>автоконфігурацію Spring (auto-configuration),</li>
 *     <li>сканування компонентів у поточному пакеті та підпакетах,</li>
 *     <li>позначає цей клас як точку входу для програми.</li>
 * </ul>
 */
@SpringBootApplication
public class UserApiApplication {

    /**
     * Точка входу в застосунок.
     * <p>
     * Запускає вбудований веб-сервер (Tomcat/Jetty тощо) і піднімає весь Spring-контекст.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }
}