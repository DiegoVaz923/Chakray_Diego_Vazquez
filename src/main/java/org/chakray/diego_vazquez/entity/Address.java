package org.chakray.diego_vazquez.entity;

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
    private String countryCode;
}
