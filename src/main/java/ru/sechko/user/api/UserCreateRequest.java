package ru.sechko.user.api;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank(message = "Поле 'имя' не должно быть пустым")
        String firstName,
        @NotBlank(message = "Поле 'фамилия' не должно быть пустым")
        String lastName,
        String email
) {
}
