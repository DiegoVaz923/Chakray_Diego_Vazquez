package org.chakray.diego_vazquez.service;

import lombok.RequiredArgsConstructor;
import org.chakray.diego_vazquez.dto.request.CreateUserRequest;
import org.chakray.diego_vazquez.dto.request.UpdateUserRequest;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.exception.ResourceNotFoundException;
import org.chakray.diego_vazquez.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(String sortedBy, String filter) {

        List<User> users = userRepository.findAll();

        if (filter != null && !filter.isBlank()) {

            String[] parts = filter.split("\\+");
            if (parts.length == 3) {

                String field = parts[0];
                String operation = parts[1];
                String value = parts[2];

                users = users.stream()
                    .filter(user -> {

                        String fieldValue = getFieldValue(user, field);

                        return switch (operation) {

                            case "co" -> fieldValue.contains(value);
                            case "eq" -> fieldValue.equals(value);
                            case "sw" -> fieldValue.startsWith(value);
                            case "ew" -> fieldValue.endsWith(value);

                            default -> false;
                        };
                    })
                    .toList();
            }
        }

        if (sortedBy != null && !sortedBy.isBlank()) {
            switch (sortedBy) {
                case "id" -> users = users.stream()
                                .sorted(Comparator.comparing(User::getId))
                                .toList();
                case "email" -> users = users.stream()
                                .sorted(Comparator.comparing(User::getEmail))
                                .toList();
                case "name" -> users = users.stream()
                                .sorted(Comparator.comparing(User::getName))
                                .toList();
                case "phone" -> users = users.stream()
                                .sorted(Comparator.comparing(User::getPhone))
                                .toList();
                case "tax_id" -> users = users.stream()
                                .sorted(Comparator.comparing(User::getTaxId))
                                .toList();
                case "created_at" -> users = users.stream()
                                .sorted(Comparator.comparing(User::getCreatedAt))
                                .toList();
            }
        }
        return users;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));
    }

    public User createUser(CreateUserRequest request) {

        boolean taxIdExists = userRepository.findAll().stream().anyMatch(u -> u.getTaxId().equals(request.getTaxId()));

        if(taxIdExists) {
            throw new IllegalArgumentException("tax id already exists" + request.getTaxId());
        }

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

    private String getFieldValue(User user, String field) {

        return switch (field) {
            case "email" -> user.getEmail();
            case "name" -> user.getName();
            case "phone" -> user.getPhone();
            case "tax_id" -> user.getTaxId();
            case "id" -> user.getId().toString();
            case "created_at" -> user.getCreatedAt().toString();
            default -> "";
        };
    }
}
