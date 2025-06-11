package br.unitins.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.avaliacao.AvaliacaoDTO;
import br.unitins.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.model.Avaliacao;
import br.unitins.service.avaliacao.AvaliacaoService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {
    @Inject
    AvaliacaoService service;

    @Inject
    JsonWebToken jwt;

    @Transactional
    @POST
//    @RolesAllowed({"Admin"})
    public Response insert(AvaliacaoDTO dto){
        try {
            
            AvaliacaoResponseDTO responseDTO = service.insert(dto);
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            Log.error("Erro ao cadastrar uma Avaliacao: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar a Avaliacao.").build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"User","Admin"})
    public AvaliacaoResponseDTO update(AvaliacaoDTO dto, @PathParam("id") Long id) {
        Log.info("Atualizando a Avaliacao: "+id);
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
//    @RolesAllowed({"User","Admin"})
    public void delete(@PathParam("id") Long id) {
        Log.info("Deletando a Avaliacao:" +id);
        service.delete(id);
    }

    @GET
//    @RolesAllowed({"User","Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize){

        Log.info("Buscando todas as Avaliacaos cadastradas.");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }

    @GET
    @Path("/minhasAvaliacoes")
//    @RolesAllowed({"User"})
    public Response minhasAvaliacoes( 
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize){

        Log.info("Executando o método minhasAvaliacoes() de avaliacao. ");
        try {
            return Response.ok(service.minhasAvaliacoes(page, pageSize)).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
//    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Buscando a Avaliacao expecificada pelo id: "+id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
//    @RolesAllowed({"User","Admin"})
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize ){

        Log.info("Buscando a Avaliacao expecificada pelo nome: "+nome);
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

    @GET
    @Path("/pendentes")
    public Response listarAvaliacoesPendentes(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize ) {
        return Response.ok(service.listarAvaliacoesPendentes(page, pageSize)).build();
    }

    @PUT
    @Path("/{id}/aprovar")
    public AvaliacaoResponseDTO aprovarAvaliacao(@PathParam("id") Long id) {
        Log.info("Atualizando a Avaliacao: "+id);
        return service.aprovarAvaliacao(id);
    }


}
