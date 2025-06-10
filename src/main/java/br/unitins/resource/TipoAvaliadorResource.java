package br.unitins.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.tipoAvaliador.TipoAvaliadorDTO;
import br.unitins.dto.tipoAvaliador.TipoAvaliadorResponseDTO;
import br.unitins.service.tipoAvaliador.TipoAvaliadorService;
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

@Path("/tipoavaliador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoAvaliadorResource {
    @Inject
    TipoAvaliadorService service;

    @Inject
    JsonWebToken jwt;

    @Transactional
    @POST
    @RolesAllowed({"Admin"})
    public Response insert(TipoAvaliadorDTO dto){
        try {
            
            TipoAvaliadorResponseDTO responseDTO = service.insert(dto);
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            Log.error("Erro ao cadastrar um novo Tipo de Avaliador: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar o Tipo de Avaliador.").build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Avaliador","Admin"})
    public TipoAvaliadorResponseDTO update(TipoAvaliadorDTO dto, @PathParam("id") Long id) {
        Log.info("Atualizando o TipoAvaliador: "+id);
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Avaliador","Admin"})
    public void delete(@PathParam("id") Long id) {
        Log.info("Deletando a TipoAvaliador:" +id);
        service.delete(id);
    }

    @GET
    //@RolesAllowed({"Avaliador","Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize){

        Log.info("Buscando todas os TipoAvaliador cadastradas.");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Avaliador","Admin"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Buscando o TipoAvaliador expecificada pelo id: "+id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"Avaliador","Admin"})
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize ){

        Log.info("Buscando a TipoAvaliador expecificada pelo nome: "+nome);
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
