package br.com.adrianosb.controller;

import br.com.adrianosb.dto.LoginRequest;
import br.com.adrianosb.security.JwtUtil;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.Header;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AuthControllerIntegrationTest {

    @Inject
    private JwtUtil jwtUtil;

    @Test
    public void testInvalidToken() {
        given()
                .when()
                .get("/foo-bar")
                .then()
                .statusCode(401);
    }

    @Test
    public void testValidToken() {
        String token = given()
                .contentType("application/json")
                .body(new LoginRequest("user", "user"))
                .when()
                .post("/signin")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        String usernameFromToken = jwtUtil.getUsernameFromToken(token);
        assertEquals("user", usernameFromToken);
    }

    @Test
    public void testAuthentication() {
        String token = jwtUtil.generateToken("user");

        given()
                .header(new Header("Authorization", "Bearer " + token))
                .when()
                .get("/foo-bar")
                .then()
                .statusCode(204);
    }
}
