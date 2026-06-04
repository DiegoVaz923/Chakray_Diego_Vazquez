package org.chakray.diego_vazquez.dto.response;

import org.chakray.diego_vazquez.entity.Address;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String taxId;
    private LocalDateTime createdAt;
    private List<Address> addresses;
}
