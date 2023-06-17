package com.example.todorest.dto.categoriesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTodoRequestDTO {
    private String categoryName;
    private String title;

}
