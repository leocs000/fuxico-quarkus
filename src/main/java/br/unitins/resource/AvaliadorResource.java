package br.unitins.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.avaliador.AvaliadorDTO;
import br.unitins.service.avaliador.AvaliadorService;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/avaliadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliadorResource {
    
    @Inject
    AvaliadorService service;

    @Inject
    JsonWebToken jwt;

    @PUT
    @Transactional
    @RolesAllowed({ "Avaliador"})
    @Path("/{id}")
    //public Response update(AvaliadorDTO dto, @PathParam("id") Long id) {
    public Response update(AvaliadorDTO dto) {
        String login = jwt.getSubject();
        Long idAvaliador = service.findByUsuario(login).getId();

        if (idAvaliador == null) {
            System.out.println("deu ruim pessoal");
        }
   
        Log.info("Atualizando dados de um Avaliador."+dto.getNome());
        service.update(dto, idAvaliador);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Log.info("Deletando um Avaliador."+ id);
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize) {

        Log.info("Buscando todos os Avaliadors.");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Log.info("Buscando um Avaliador expecificando o id."+ id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    @Path("/search/nome/{nome}")
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize) {

        Log.info("Buscando um Avaliador expecificando o nome." + nome);
        return Response.ok(service.findByNome(nome, page, pageSize)).build();
    }

    @GET
    @RolesAllowed({"User", "Admin"})
    @Path("/dados-pessoais")
    public Response findByDadosPessoais(){
        String login = jwt.getSubject();
        Log.info(login+" Verificando os dados pessoais."  );
        return Response.ok(service.findByUsuario(login)).build();
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
}
