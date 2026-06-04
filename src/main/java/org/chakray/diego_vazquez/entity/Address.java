package org.chakray.diego_vazquez.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    Long id;
    String name;
    String street;
    String countryCode;
}
