package com.example.todorest.mapper;

import com.example.todorest.dto.categoriesDTO.CreateTodoRequestDTO;
import com.example.todorest.dto.categoriesDTO.CreateTodoResponseDTO;
import com.example.todorest.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo mapTodo(CreateTodoRequestDTO request);

    @Mapping(target = "createCategoryRequestDTO", source = "category")
    @Mapping(target = "updateUserRequestDTO", source = "user")
    CreateTodoResponseDTO mapToResponseDTO(Todo todo);


}
