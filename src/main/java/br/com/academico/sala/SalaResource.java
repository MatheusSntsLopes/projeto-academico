package br.com.academico.sala;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/salas")
@Tag(name = "Salas")
public class SalaResource {
    private Sala sala;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Salas",
        description = "Recuperar uma lista completa de salas com todos os dados"
    )
    public Response recuperar(){
        List<Sala> listaSalas = new ArrayList<Sala>();
        listaSalas.add(new Sala(true, false, true, "43", 30));
        listaSalas.add(new Sala(false, true, false, "40", 20));
        return Response.ok(listaSalas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Operation(
        summary = "Recuperar Salas",
        description = "Recuperar apenas uma sala a partir de seu id"
    )
    public Response recuperar(@PathParam("id")int id){
        sala = new Sala(true,true, true,"40",30);
        return Response.ok(sala, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Salas",
        description = "Cria uma sala completa"
    )
    public Response criar(Sala sala){
        sala.setId(2);
        return Response.status(Response.Status.CREATED).entity(sala).build();
    }
     
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Sala",
        description = "Cria uma sala completa"
    )
    public Response atualizar(@PathParam("id")int id, Sala sala){
     return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
        summary = "Deletar uma Sala",
        description = "Deletar uma sala a partir de seu id"
    )
    public Response deletar(@PathParam("id")int id){
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
