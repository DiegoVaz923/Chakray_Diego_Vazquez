package org.chakray.diego_vazquez.controller;

import lombok.RequiredArgsConstructor;
import org.chakray.diego_vazquez.dto.request.LoginRequest;
import org.chakray.diego_vazquez.dto.response.LoginResponse;
import org.chakray.diego_vazquez.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        if(response.isAuthenticated()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }
}
