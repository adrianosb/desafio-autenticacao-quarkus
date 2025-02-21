package br.com.adrianosb.controller;

import br.com.adrianosb.dto.LoginRequest;
import br.com.adrianosb.service.AuthenticationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class AuthController {

    @Inject
    private AuthenticationService userService;

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signin(LoginRequest loginRequest) {
        try {
            String token = userService.signin(loginRequest);
            return Response.ok(token).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }

    }

    @GET
    @Path("/foo-bar")
    public Response foobar() {
        return Response.noContent().build();
    }

}
