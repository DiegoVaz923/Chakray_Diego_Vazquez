package org.chakray.diego_vazquez.config;

import lombok.RequiredArgsConstructor;
import org.chakray.diego_vazquez.entity.Address;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {

        userRepository.save(
                User.builder()
                        .id(UUID.randomUUID())
                        .email("diego@example.com")
                        .name("Diego Vazquez")
                        .phone("7771082089")
                        .password("password123")
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
                        .id(UUID.randomUUID())
                        .email("john@example.com")
                        .name("John Doe")
                        .phone("5551234567")
                        .password("password123")
                        .taxId("DOAJ920202DEF")
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
                        .id(UUID.randomUUID())
                        .email("jane@example.com")
                        .name("Jane Doe")
                        .phone("1234567890")
                        .password("password123")
                        .taxId("DOAJ930303GHI")
                        .createdAt(LocalDateTime.now())
                        .addresses(List.of(
                                Address.builder()
                                        .id(3L)
                                        .name("Home")
                                        .street("Insurgentes 300")
                                        .countryCode("MX")
                                        .build()
                        ))
                        .build()
        );
    }

}
