package com.kamal.school.web;

import com.kamal.school.domain.Role;
import com.kamal.school.domain.User;
import com.kamal.school.dto.JwtResponse;
import com.kamal.school.dto.LoginRequest;
import com.kamal.school.dto.RegisterRequest;
import com.kamal.school.security.JwtService;
import com.kamal.school.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestParam String username,
//                                         @RequestParam String password,
//                                         @RequestParam(defaultValue = "STUDENT") Role role) {
//        var u = userService.register(username, password, role);
//        return ResponseEntity.ok(u);
//    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        var u = userService.register(request.getUsername(), request.getPassword(), request.getRole());
        return ResponseEntity.ok(u);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtService.generateToken(request.getUsername(), Map.of("roles", auth.getAuthorities()));
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
