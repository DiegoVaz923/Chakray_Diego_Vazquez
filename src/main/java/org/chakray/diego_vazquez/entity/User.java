package org.chakray.diego_vazquez.entity;

import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    UUID id;
    String email;
    String name;
    String phone;
    String password;
    String taxId;
    LocalDateTime createdAt;
    List<Address> addresses;
}
