package org.chakray.diego_vazquez.dto.request;

import lombok.*;
import org.chakray.diego_vazquez.entity.Address;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String email;
    private String name;
    private String phone;
    private String password;
    private String taxId;
    private List<Address> addresses;
}
