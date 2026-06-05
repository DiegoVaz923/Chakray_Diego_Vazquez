package org.chakray.diego_vazquez.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.chakray.diego_vazquez.entity.Address;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^(\\+?\\d{1,3})?\\d{10}$",message = "Must contain 10 digits (optional country code)")
    private String phone;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "^[A-Z&Ñ]{3,4}\\d{6}[A-Z0-9]{3}$",  message = "Invalid Tax ID format")
    private String taxId;
    private List<Address> addresses;
}
