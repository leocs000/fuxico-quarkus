package br.unitins.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.topico.TopicoDTO;
import br.unitins.dto.topico.TopicoResponseDTO;
import br.unitins.service.topico.TopicoService;
import io.quarkus.logging.Log;
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

@Path("/topicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicoResource {

    @Inject
    TopicoService service;

    @Inject
    JsonWebToken jwt;

    @Transactional
    @POST
//    @RolesAllowed({"Admin"})
    public Response insert(TopicoDTO dto){
        try {
            
            TopicoResponseDTO responseDTO = service.insert(dto);
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            Log.error("Erro ao cadastrar uma Topico: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar a Topico.").build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"User","Admin"})
    public TopicoResponseDTO update(TopicoDTO dto, @PathParam("id") Long id) {
        Log.info("Atualizando a Topico: "+id);
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"User","Admin"})
    public void delete(@PathParam("id") Long id) {
        Log.info("Deletando a Topico:" +id);
        service.delete(id);
    }

    @GET
//    @RolesAllowed({"User","Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize){

        Log.info("Buscando todas as Topicos cadastradas.");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
//    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Buscando a Topico expecificada pelo id: "+id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
//    @RolesAllowed({"User","Admin"})
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize ){

        Log.info("Buscando a Topico expecificada pelo nome: "+nome);
        return Response.ok(service.findByNome(nome, page, pageSize)).build();
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
