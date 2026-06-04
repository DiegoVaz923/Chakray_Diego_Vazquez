package org.chakray.diego_vazquez.dto.response;

import org.chakray.diego_vazquez.entity.Address;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponse {
    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String taxId;
    private LocalDateTime createdAt;
    private List<Address> addresses;
}
