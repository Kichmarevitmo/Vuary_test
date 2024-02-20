package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.WorkerRole;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    //@NotNull(message = "Имя пользователя не может быть пустым")
    private String username;

    //@NotNull(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 16, message = "Пароль должен содержать от 6 до 16 символов")
    private String password;

    //@NotNull(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Адрес электронной почты должен быть валидным")
    private String email;

    //@NotNull(message = "Фамилия не может быть пустой")
    private String lastname;

    //@NotNull(message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    //@NotNull(message = "Роль работника не может быть пустой")
    private String workerRole;

    //@NotNull(message = "Дата рождения не может быть пустой")
    private String dateOfBirth;
    //
}
