package org.chakray.diego_vazquez.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String taxId;
    private String password;
}
