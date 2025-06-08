package br.unitins.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.usuario.UsuarioDTO;
import br.unitins.service.usuario.UsuarioService;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @Inject
    JsonWebToken jwt;
    
    @POST
    public Response insertAvaliador(UsuarioDTO dto) {
        Log.info("Cadastrando um Avaliador.");
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Avaliador","Admin"})
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        Log.info("Fazendo update de um usuario.");
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        Log.info("Deletando um usuario.");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
 
    @GET
    @RolesAllowed({"Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize) {

        Log.info("Busca de todos os usuarios");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id) {
        Log.info("Busca de um usuario especificado pelo id.");
       return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/login/{login}")
    @RolesAllowed({"Admin"})
    public Response findByNome(
            @PathParam("login") String login,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize) {

        Log.info("Busca de um usuario especificado pelo login.");
        return Response.ok(service.findByNome(login, page, pageSize)).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return service.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return service.countByNome(nome);
    }

    @GET
    @Path("/my-user")
    @RolesAllowed({"User","Admin"})
    public Response findMyUser() {
        Log.info("Busca do proprio usuario.");
       return Response.ok(service.findMyUser()).build();
    }
}
