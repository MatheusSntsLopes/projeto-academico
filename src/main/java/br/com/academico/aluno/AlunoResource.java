package br.com.academico.aluno;

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

import br.com.academico.endereco.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/alunos")
@Tag(name = "Alunos")
public class AlunoResource {
  
  private Aluno aluno;
  private Endereco endereco = new Endereco(49700L, "Rua C", "Atalaia", "Aracaju", "Sergipe");
  private List<Nota> listaNotas = new ArrayList<Nota>();
  

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
    summary = "Listar Alunos",
    description = "Recuperar uma lista completa de professores com todos os dados"
)
  public Response recuperar() {
    List<Aluno> listaAlunos = new ArrayList<Aluno>();
    listaAlunos.add(new Aluno("Joao", "Souza", "Sergipe", 21, 223344, "M", "999-999-999-11", "Informatica", true));
    listaAlunos.add(new Aluno("Luiz", "Otavio", "Bahia", 18, 333222, "M", "444-222-111-33", "Informatica", true));
    listaNotas.add(new Nota(8, 1));
    listaNotas.add(new Nota(9, 2));
    for (Aluno aluno : listaAlunos) {
      aluno.setEndereco(endereco);
      aluno.setNotas(listaNotas);
      aluno.calcularMediaAritimetica();
      aluno.mediaPonderada();
    }
    return Response.ok(listaAlunos, MediaType.APPLICATION_JSON).build();
  }

  @GET
    @Path("{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
      summary = "Recuperar Alunos",
      description = "Recuperar apenas um aluno a partir de seu id"
  )
    public Response recuperar(@PathParam("matricula") int matricula) {
        aluno = new Aluno("Maria", "Fernanda", "Alagoas", 15, 3322112, "F", "777-555-444-22", "Comercio", true);
        listaNotas.add(new Nota(10, 1));
        listaNotas.add(new Nota(9, 1));
        aluno.setNotas(listaNotas);
        aluno.setEndereco(endereco);
        aluno.calcularMediaAritimetica();
        aluno.mediaPonderada();
        return Response.ok(aluno, MediaType.APPLICATION_JSON).build();
    }

  @GET
    @Path("{matricula}/notas")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
      summary = "Recuperar notas do Aluno",
      description = "recupera uma lista de notas de um aluno completo"
  )
    public Response recuperarNotas(@PathParam("matricula") int matricula) {
        listaNotas.add(new Nota(10, 1));
        listaNotas.add(new Nota(9, 1));
        int id = 1;
        for (Nota nota : listaNotas) {
          nota.setId(id);
          nota.setMatricula(matricula);
          id++;
        }
        return Response.ok(listaNotas, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
      summary = "Criar Aluno",
      description = "Cria um aluno completo"
  )
    public Response criar(Aluno aluno){
        return Response
            .status(Response.Status.CREATED)
            .entity(aluno)
            .build();
    }

    @PUT
    @Path("{matricula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
      summary = "Atualizar Aluno",
      description = "Atializa um aluno a partir de seu id"
  )
    public Response atualizar(@PathParam("matricula") int matricula, Aluno aluno){
        return Response
            .status(Response.Status.NO_CONTENT)
            .build();
    }

    @DELETE
    @Path("{matricula}")
    @Operation(
      summary = "Deletar um Aluno",
      description = "Deletar um aluno a partir de seu id"
  )
    public Response deletar(@PathParam("matricula") int matricula){
        return Response
            .status(Response.Status.NO_CONTENT)
            .build();
    }
}
