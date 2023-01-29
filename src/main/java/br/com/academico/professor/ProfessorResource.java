package br.com.academico.professor;

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

@Path("/professores")
@Tag(name = "Professores")
public class ProfessorResource {
    private Professor professor;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Professores",
        description = "Recuperar uma lista completa de professores com todos os dados"
    )
    public Response recuperar(){
        List<Professor> listaProfessores = new ArrayList<Professor>();
        listaProfessores.add(new Professor("João", "Santos", "Sergipe", 44, 123456, "M","999999999",9999,230));
        listaProfessores.add(new Professor("Lucas", "Souza", "Alagoas", 33, 654321, "M","111111111",8888,123));
        return Response.ok(listaProfessores, MediaType.APPLICATION_JSON).build();

    }
    @GET
    @Path("{id}")
    @Operation(
        summary = "Recuperar Professores",
        description = "Recuperar apenas um professor a partir de seu id"
    )
    public Response recuperar(@PathParam("id") int id) {
        professor = new Professor("Paulo", "Silva", "Bahia", 55, 223344,"M","00000000",55555,300);
        professor.setId(id);
        return Response.ok(professor, MediaType.APPLICATION_JSON).build();

    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Professor",
        description = "Cria um professor completo"
    )
    public Response criar(Professor professor) {
        professor.setId(1);
        return Response
                .status(Response.Status.CREATED).entity(professor).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Professor",
        description = "Cria um professor completo"
    )
    public Response atualizar(@PathParam("id")int id,Professor professor) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
        summary = "Deletar um Professor",
        description = "Deletar um professor a partir de seu id"
    )
    public Response deletar(@PathParam("id") int id) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}

