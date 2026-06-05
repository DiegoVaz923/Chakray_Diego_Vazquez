package org.chakray.diego_vazquez.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chakray.diego_vazquez.dto.request.CreateUserRequest;
import org.chakray.diego_vazquez.dto.request.UpdateUserRequest;
import org.chakray.diego_vazquez.dto.response.UserResponse;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.mapper.UserMapper;
import org.chakray.diego_vazquez.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponse> getUsers(
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String filter) {

        return userService.getUsers(sortedBy, filter).stream()
                .map(userMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id) {
        return userMapper.toResponse(userService.getUserById(id));
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userMapper.toResponse(userService.createUser(request));
    }

    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest request) {
        return userMapper.toResponse(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

}
