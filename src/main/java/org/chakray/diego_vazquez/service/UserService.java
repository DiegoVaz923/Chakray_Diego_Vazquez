package org.chakray.diego_vazquez.service;

import lombok.RequiredArgsConstructor;
import org.chakray.diego_vazquez.dto.request.CreateUserRequest;
import org.chakray.diego_vazquez.dto.request.UpdateUserRequest;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.exception.ResourceNotFoundException;
import org.chakray.diego_vazquez.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));
    }

    public User createUser(CreateUserRequest request) {

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .name(request.getName())
                .phone(request.getPhone())
                .password(request.getPassword())
                .taxId(request.getTaxId())
                .addresses(request.getAddresses())
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    public User updateUser(UUID id, UpdateUserRequest request) {

        User user = getUserById(id);

        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());
        user.setTaxId(request.getTaxId());
        user.setAddresses(request.getAddresses());

        return user;
    }

    public void deleteUser(UUID id) {

        getUserById(id);

        userRepository.delete(id);
    }
}
