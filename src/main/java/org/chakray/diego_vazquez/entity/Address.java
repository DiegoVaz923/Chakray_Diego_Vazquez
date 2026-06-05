package org.chakray.diego_vazquez.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;
    private String name;
    private String street;
    @JsonProperty("country_code")
    private String countryCode;
}
