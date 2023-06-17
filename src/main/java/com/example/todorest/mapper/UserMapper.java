package com.example.todorest.mapper;

import com.example.todorest.dto.userDTO.CreateUserRequestDTO;
import com.example.todorest.dto.userDTO.CreateUserResponseDTO;
import com.example.todorest.dto.userDTO.UpdateUserRequestDTO;
import com.example.todorest.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUser(CreateUserRequestDTO request);

    User mapToUser(UpdateUserRequestDTO request);

    CreateUserResponseDTO mapToDto(User user);

}
