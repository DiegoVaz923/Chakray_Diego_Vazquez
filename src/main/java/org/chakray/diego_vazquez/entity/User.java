package org.chakray.diego_vazquez.entity;

import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String taxId;
    private LocalDateTime createdAt;
    private List<Address> addresses;
}
