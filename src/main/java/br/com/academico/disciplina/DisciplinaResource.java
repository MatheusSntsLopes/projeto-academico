package br.com.academico.disciplina;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
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

@Path("/disciplinas")
@Tag(name = "Disciplinas")
public class DisciplinaResource {
    private Disciplina disciplina;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Disciplinas",
        description = "Recuperar uma lista completa de disciplinas com todos os dados"
    )
    public Response recuperar() {

        List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
        listaDisciplina.add(new Disciplina("Programação", 150));
        listaDisciplina.add(new Disciplina("Engenharia Software", 300));
        return Response.ok(listaDisciplina, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{id}")
    @Operation(
        summary = "Recuperar Disciplinas",
        description = "Recuperar apenas uma disciplina a partir de seu id"
    )
    public Response recuperar(@PathParam("id") int id) {
        disciplina = new Disciplina("Programação", 150);
        disciplina.setId(id);
        return Response.ok(disciplina, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Disciplinas",
        description = "Cria uma disciplina completa"
    )
    public Response criar(@Valid Disciplina disciplina) {
        disciplina.setId(10);
        return Response.status(Response.Status.CREATED).entity(disciplina).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Disciplina",
        description = "Cria uma disciplina completa"
    )
    public Response atualizar(@PathParam("id") int id, Disciplina disciplina) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
        summary = "Deletar uma Disciplina",
        description = "Deletar uma disciplina a partir de seu id"
    )
    public Response deletar(@PathParam("id") int id) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
