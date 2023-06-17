package com.example.todorest.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
}
