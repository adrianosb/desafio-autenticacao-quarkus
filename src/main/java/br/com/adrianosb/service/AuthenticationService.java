package br.com.adrianosb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.adrianosb.dto.LoginRequest;
import br.com.adrianosb.model.User;
import br.com.adrianosb.security.JwtUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    private JwtUtil jwtUtil;

    private final List<User> usersMock = new ArrayList<>();

    public AuthenticationService() {
        this.usersMock.add(new User("admin", "admin"));
        this.usersMock.add(new User("user", "user"));
    }

    public String signin(LoginRequest loginRequest) {

        Optional<User> userOpt = usersMock.stream()
                .filter(u -> u.username().equals(loginRequest.username()))
                .findFirst();

        if (userOpt.isEmpty() || !userOpt.get().password().equals(loginRequest.password())) {
            throw new RuntimeException("Usuário ou senha inválidos.");
        }

        String token = jwtUtil.generateToken(loginRequest.username());
        return token;
    }

    public boolean authenticate(String token) {

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtUtil.validateToken(token);
    }

}
