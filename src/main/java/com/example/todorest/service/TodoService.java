package com.example.todorest.service;

import com.example.todorest.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo saveToto(Todo todo);

    List<Todo> findCurrentUserTodos(int currentUserId);

    Todo findTodoById(int todoId);

    void deleteTodoById(int todoId);
}
