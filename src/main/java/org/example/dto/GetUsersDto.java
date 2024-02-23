package org.example.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Images_Module.FileData;
import org.example.model.Role;
import org.example.model.WorkerRole;
import org.example.token.Token;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersDto {
    private String firstname;
    private String email;
    private String lastname;
    private String phoneNumber;

    private String workerRole;

    private String dateOfBirth;

    private String photo;
    private Boolean activationCode;
    private String role;
    private String id;
}
