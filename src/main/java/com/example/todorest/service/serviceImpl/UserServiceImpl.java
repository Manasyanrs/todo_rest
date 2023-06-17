package com.example.todorest.service.serviceImpl;

import com.example.todorest.entity.User;
import com.example.todorest.repository.UserRepository;
import com.example.todorest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserData(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        byEmail.get().setName(user.getName());
        byEmail.get().setSurname(user.getSurname());
        userRepository.save(byEmail.get());
    }
}
