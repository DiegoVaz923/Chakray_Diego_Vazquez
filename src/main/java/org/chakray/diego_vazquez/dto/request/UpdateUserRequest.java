package org.chakray.diego_vazquez.dto.request;

import org.chakray.diego_vazquez.entity.Address;

import java.util.List;

public class UpdateUserRequest {
    private String email;
    private String name;
    private String phone;
    private String password;
    private String taxId;
    private List<Address> addresses;
}
