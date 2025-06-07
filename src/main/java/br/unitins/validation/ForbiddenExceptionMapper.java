package br.unitins.validation;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import io.quarkus.security.ForbiddenException;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException>{

    @Override
    public Response toResponse(ForbiddenException exception) {
        // Aqui você pode criar uma resposta personalizada, incluindo detalhes do erro
        return Response.status(Response.Status.FORBIDDEN)
               .entity("Você não tem permissão para essa ação.")
               .build();
    }

    
}
