package br.com.adrianosb.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.adrianosb.service.AuthenticationService;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class RequestInterceptor implements ContainerRequestFilter {

    private final Set<String> allowedPaths = new HashSet<>();

    @Inject
    AuthenticationService authenticationService;

    public RequestInterceptor() {
        allowedPaths.add("/signin");
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();

        if (allowedPaths.contains(path)) {
            return;
        }

        // Recupera o header Authorization
        List<String> authorizationHeaders = requestContext.getHeaders().get("Authorization");
        if (authorizationHeaders == null || authorizationHeaders.isEmpty() ||
                !isAuthenticated(authorizationHeaders.get(0))) {
            abortRequest(requestContext);
        }
    }

    private void abortRequest(ContainerRequestContext context) {
        context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private boolean isAuthenticated(String token) {
        return authenticationService.authenticate(token);
    }
}
