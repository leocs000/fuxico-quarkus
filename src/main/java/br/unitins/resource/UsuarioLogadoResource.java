package br.unitins.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import br.unitins.dto.usuario.AlterarLoginUsuarioDTO;
import br.unitins.dto.usuario.AlterarSenhaUsuarioDTO;
import br.unitins.service.usuario.UsuarioService;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@APIResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso.")
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @GET
    @RolesAllowed({ "Avaliador", "Admin" })
    public Response getMeuUsuario() {

        // Obtendo o login pelo token jwt
        String login = jwt.getSubject();
        Log.info("Pegando o usuario logado string: " + login);
        Log.info("Pegando o usuario logado");
        return Response.ok(usuarioService.findByLogin(login)).build();
    }

    @Path("/usuariologado/alterarsenha")
    @PUT
    @RolesAllowed({"Avaliador", "Admin"})
    public Response putInfos(AlterarSenhaUsuarioDTO senhaUsuarioDTO){
        String login = jwt.getSubject();
        Log.info("Pegando o usuario logado string: " + login);
        Log.info("Alterando a senha do usuário logado");
        usuarioService.alterarSenha(senhaUsuarioDTO, login);
        return Response.noContent().build();
    }

    @Path("/usuariologado/alterarlogin")
    @PUT
    @RolesAllowed({"Avaliador", "Admin"})
    public Response putInfos(AlterarLoginUsuarioDTO loginUsuarioDTO){
        String login = jwt.getSubject();
        Log.info("Pegando o usuario logado string: " + login);
        Log.info("Alterando o login do usuário logado");
        usuarioService.alterarLogin(loginUsuarioDTO, login);
        return Response.noContent().build();
    }

}
