package br.unitins.resource;

import br.unitins.dto.avaliador.AvaliadorDTO;
import br.unitins.dto.avaliador.AvaliadorResponseDTO;
import br.unitins.service.avaliador.AvaliadorService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cadastro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroAvaliadorResource {
    @Inject
    AvaliadorService service;

    @POST
    public Response insert(@Valid AvaliadorDTO dto) {
        Log.info("Inserindo um Avaliador."+dto.getLogin());
        AvaliadorResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }
}
