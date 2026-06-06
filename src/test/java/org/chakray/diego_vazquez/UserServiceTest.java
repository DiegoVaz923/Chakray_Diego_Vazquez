package org.chakray.diego_vazquez;

import org.chakray.diego_vazquez.dto.request.CreateUserRequest;
import org.chakray.diego_vazquez.entity.User;
import org.chakray.diego_vazquez.exception.ResourceNotFoundException;
import org.chakray.diego_vazquez.repository.UserRepository;
import org.chakray.diego_vazquez.service.AesEncryptionService;
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
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AesEncryptionService aesEncryptionService;
    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {

        user1 = User.builder()
                .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .email("diego@hotmail.com")
                .name("Diego Vazquez")
                .phone("7771082089")
                .password("password123")
                .taxId("VASD950101ABC")
                .createdAt(LocalDateTime.now())
                .addresses(List.of())
                .build();
        ;
        user2 = User.builder()
                .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .email("leslie@gmail.com")
                .name("Leslie Vega")
                .phone("5551234567")
                .password("wordpass123")
                .taxId("VESL920202DEF")
                .createdAt(LocalDateTime.now())
                .addresses(List.of())
                .build();
    }

    @Test
    void getUsers_NoFilterSort_ReturnallUsers(){ //Sin filtro ni ordenamiento deberia retornar todos los usuarios
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        List<User> result = userService.getUsers(null,null);
        assertEquals(2,result.size());
    }

    @Test
    void getUsers_SortByName_ReturnSortedList(){ //Ordenado por nombre, Diego y Leslie , debería devolver user1, user2
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        List<User> result = userService.getUsers("name",null);
        assertEquals("Diego Vazquez", result.get(0).getName());
        assertEquals("Leslie Vega", result.get(1).getName());
    }

    @Test
    void getUsers_filterByNameContains_ReturnFilter(){ //Si filtras por nombre el resultado debería ser solo las coicidencias
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        List<User> result = userService.getUsers(null,"name+co+Diego");
        assertEquals(1, result.size());
        assertEquals("Diego Vazquez", result.get(0).getName());
    }

    @Test
    void getUsers_filterByEmailEndsWith_ReturnsFilter(){ //Si filtras por correo, debería de salir solo las coincidencias de -mail
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        List<User> result = userService.getUsers(null, "email+ew+gmail.com"); //user1 es hotmail, no gmail
        assertEquals(1, result.size());
        assertEquals("leslie@gmail.com", result.get(0).getEmail());
    }

    @Test
        void getUserById_existingId_ReturnsUser(){ //Si existe RFC devuelve al usuario
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        User result = userService.getUserById(user1.getId());
        assertEquals(user1.getId(), result.getId());
    }

    @Test
    void getUserById_nonExistingId_throwsException(){ //Si no hay identificador debería devolver excepcion
        UUID randomId = UUID.randomUUID();
        when(userRepository.findById(randomId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(randomId));
    }

    @Test
    void createUser_duplicateTaxId_throwsException(){ //Si ya hay un RFC en el sistema devuelve excepcion al intentar agregar uno nuevo con el mismo RFC
        when(userRepository.findAll()).thenReturn(List.of(user1));
        CreateUserRequest request = new CreateUserRequest();
        request.setTaxId("VASD950101ABC");
        request.setEmail("nuevo@mail.com");
        request.setName("Nuevo");
        request.setPhone("5559999999");
        request.setPassword("pass");
        request.setAddresses(List.of());

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser(){ //Crea nuevo usuario si todos los campos están correctos y no se repiten
        when(userRepository.findAll()).thenReturn(List.of());
        when(aesEncryptionService.encrypt(any())).thenReturn("newpass");
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        CreateUserRequest request = new CreateUserRequest();
        request.setTaxId("VASD950101AB2");
        request.setEmail("nuev2@mail.com");
        request.setName("Nuevo2");
        request.setPhone("5559999999");
        request.setPassword("pass");
        request.setAddresses(List.of());

        User result = userService.createUser(request);
        assertNotNull(result.getId());
        assertEquals("VASD950101AB2", result.getTaxId());
    }

    @Test
    void deleteUser(){ //Elimina usuario existente
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        assertDoesNotThrow(() -> userService.deleteUser(user1.getId()));
        verify(userRepository, times(1)).delete(user1.getId());
    }

}
