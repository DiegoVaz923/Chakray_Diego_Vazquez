package org.chakray.diego_vazquez.config;

import lombok.RequiredArgsConstructor;
import org.chakray.diego_vazquez.entity.Address;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.repository.UserRepository;
import org.chakray.diego_vazquez.service.AesEncryptionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AesEncryptionService aesEncryptionService;

    @Override
    public void run(String... args) {

        userRepository.save(
                User.builder()
                        .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                        .email("diego@hotmail.com")
                        .name("Diego Vazquez")
                        .phone("7771082089")
                        .password(aesEncryptionService.encrypt("password123"))
                        .taxId("VASD950101ABC")
                        .createdAt(LocalDateTime.now())
                        .addresses(List.of(
                                Address.builder()
                                        .id(1L)
                                        .name("Home")
                                        .street("Av. Universidad 100")
                                        .countryCode("MX")
                                        .build()
                        ))
                        .build()
        );

        userRepository.save(
                User.builder()
                        .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                        .email("leslie@gmail.com")
                        .name("Leslie Vega")
                        .phone("5551234567")
                        .password(aesEncryptionService.encrypt("wordpass123"))
                        .taxId("VESL920202DEF")
                        .createdAt(LocalDateTime.now())
                        .addresses(List.of(
                                Address.builder()
                                        .id(2L)
                                        .name("Office")
                                        .street("Reforma 200")
                                        .countryCode("MX")
                                        .build()
                        ))
                        .build()
        );

        userRepository.save(
                User.builder()
                        .id(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                        .email("john@example.com")
                        .name("John Doe")
                        .phone("5551234567")
                        .password(aesEncryptionService.encrypt("password321"))
                        .taxId("DOAJ920202DEF")
                        .createdAt(LocalDateTime.now())
                        .addresses(List.of(
                                Address.builder()
                                        .id(2L)
                                        .name("Office")
                                        .street("Insurgentes 300")
                                        .countryCode("US")
                                        .build()
                        ))
                        .build()
        );
    }

}
