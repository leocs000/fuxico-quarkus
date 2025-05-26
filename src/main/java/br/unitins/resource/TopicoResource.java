package br.unitins.resource;

import br.unitins.service.topico.TopicoService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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

    @GET
//    @RolesAllowed({"User","Admin"})
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("30") int pageSize){

        Log.info("Buscando todas os Topicos cadastrados.");
        return Response.ok(service.findByAll(page, pageSize)).build();
    }
}
