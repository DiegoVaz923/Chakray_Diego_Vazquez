package org.chakray.diego_vazquez;


import org.chakray.diego_vazquez.dto.request.CreateUserRequest;
import org.chakray.diego_vazquez.dto.request.LoginRequest;
import org.chakray.diego_vazquez.dto.response.LoginResponse;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.exception.ResourceNotFoundException;
import org.chakray.diego_vazquez.repository.UserRepository;
import org.chakray.diego_vazquez.service.AesEncryptionService;
import org.chakray.diego_vazquez.service.AuthService;
import org.chakray.diego_vazquez.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AesEncryptionService aesEncryptionService;

    @InjectMocks
    private AuthService authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(UUID.randomUUID())
                .email("diego@hotmail.com")
                .name("Diego Vazquez")
                .phone("7771082089")
                .password("encryptedPassword")
                .taxId("VASD950101ABC")
                .createdAt(LocalDateTime.now())
                .addresses(List.of())
                .build();
    }

    @Test
    void login_valid_authentication() { //Login valido
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(aesEncryptionService.decrypt("encryptedPassword")).thenReturn("password123");

        LoginRequest request = new LoginRequest("VASD950101ABC", "password123");
        LoginResponse response = authService.login(request);

        assertTrue(response.isAuthenticated());
        assertEquals("Login successful", response.getMessage());
    }

    @Test
    void login_invalid_authentication() { //Login invalido (credencial incorrecta)
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(aesEncryptionService.decrypt("encryptedPassword")).thenReturn("password123");

        LoginRequest request = new LoginRequest("VASD950101ABC", "wrongpassword");
        LoginResponse response = authService.login(request);

        assertFalse(response.isAuthenticated());
        assertEquals("Invalid credentials", response.getMessage());
    }

    @Test
    void login_not_found() { //Login invalido (no existe el user)
        when(userRepository.findAll()).thenReturn(List.of());

        LoginRequest request = new LoginRequest("NOEXISTE123", "password123");
        LoginResponse response = authService.login(request);

        assertFalse(response.isAuthenticated());
        assertEquals("User not found", response.getMessage());
    }

}
