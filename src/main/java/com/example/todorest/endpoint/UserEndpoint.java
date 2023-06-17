package com.example.todorest.endpoint;

import com.example.todorest.dto.userDTO.*;
import com.example.todorest.entity.Role;
import com.example.todorest.entity.User;
import com.example.todorest.mapper.UserMapper;
import com.example.todorest.security.CurrentUser;
import com.example.todorest.service.UserService;
import com.example.todorest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserEndpoint {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil tokenUtil;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDTO> register(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        Optional<User> getUserByEmail = userService.getUserByEmail(createUserRequestDTO.getEmail());
        if (getUserByEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }
        User user = userMapper.mapToUser(createUserRequestDTO);
        user.setPassword(passwordEncoder.encode(createUserRequestDTO.getPassword()));
        user.setRole(Role.USER);

        User save = userService.save(user);
        return ResponseEntity.ok(userMapper.mapToDto(save));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponseDto> login(@RequestBody LoginUserResponseDto loginUserResponseDto) {
        Optional<User> getUserByEmail = userService.getUserByEmail(loginUserResponseDto.getUsername());
        if (getUserByEmail.isPresent() &&
                passwordEncoder.matches(loginUserResponseDto.getPassword(), getUserByEmail.get().getPassword())) {
            String token = tokenUtil.generateToken(loginUserResponseDto.getUsername());
            return ResponseEntity.ok(new UserAuthResponseDto(token));


        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateUserResponseDTO> getUserById(@PathVariable int id) {
        Optional<User> getUserById = userService.getUserById(id);
        if (getUserById.isPresent()) {
            return ResponseEntity.ok(userMapper.mapToDto(getUserById.get()));

        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<CreateUserResponseDTO> deleteUserById(@PathVariable int id,
                                                                @AuthenticationPrincipal CurrentUser currentUser) {

        Optional<User> getUserById = userService.getUserById(id);
        if (getUserById.isPresent() && currentUser.getUser().getRole().name().equals(Role.ADMIN.name())) {
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }

    @ResponseBody
    @PutMapping()
    public ResponseEntity<CreateUserResponseDTO> updateSelfData(@RequestBody UpdateUserRequestDTO updateUserRequestDTO,
                                                                @AuthenticationPrincipal CurrentUser currentUser) {

        if (currentUser.getUser().getEmail().equals(updateUserRequestDTO.getEmail())) {
            userService.updateUserData(userMapper.mapToUser(updateUserRequestDTO));
            return ResponseEntity.status(HttpStatus.OK).build();

        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }
}
