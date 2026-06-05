package org.chakray.diego_vazquez.dto.response;

import org.chakray.diego_vazquez.entity.Address;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String email;
    private String name;
    private String phone;
    @JsonProperty("tax_id")
    private String taxId;
    @JsonProperty("created_at")
    private String createdAt;
    private List<Address> addresses;
}
