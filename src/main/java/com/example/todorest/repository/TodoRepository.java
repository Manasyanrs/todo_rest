package com.example.todorest.repository;

import com.example.todorest.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByUserId(int userId);
}
