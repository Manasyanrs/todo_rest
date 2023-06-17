package com.example.todorest.dto.userDTO;

import com.example.todorest.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResponseDTO {

    private int id;
    private String name;
    private String surname;
    private String email;
    private Role role;
}
