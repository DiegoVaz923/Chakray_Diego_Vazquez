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

    public List<User> getUsers(String countryCode, String sortType) {
        Stream<User> stream = userRepository.findAll().stream();

        if (countryCode != null && !countryCode.isBlank()) {
            stream = stream.filter(user ->
                    user.getAddresses().stream().anyMatch(address ->
                            address.getCountryCode().equalsIgnoreCase(countryCode)));
        }

        List<User> users = stream.toList();

        if (sortType != null) {
            if (sortType.equals("+name")){
                users = users.stream().sorted(Comparator.comparing(User::getName)).toList();
            }
            if (sortType.equals("-name")){
                users = users.stream().sorted(Comparator.comparing(User::getName).reversed()).toList();
            }
            return users;
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
