package org.chakray.diego_vazquez.service;

import lombok.RequiredArgsConstructor;
import org.chakray.diego_vazquez.dto.request.LoginRequest;
import org.chakray.diego_vazquez.dto.response.LoginResponse;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AesEncryptionService aesEncryptionService;

    public LoginResponse login(LoginRequest request) {
        return userRepository.findAll().stream().filter(user -> user.getTaxId().equals(request.getTaxId()))
                .findFirst().map(user -> validatePassword(user,request.getPassword()))
                .orElse(new LoginResponse("User not found",false));
    }

    private LoginResponse validatePassword(User user, String rawPassword) {
        String decrypted = aesEncryptionService.decrypt(user.getPassword());
        if (decrypted.equals(rawPassword)) {
            return new LoginResponse("Login successful", true);
        }
        return new LoginResponse("Invalid credentials", false);
    }
}
