package br.com.academico.nota;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/notas")
@Tag(name = "Notas")
public class NotaResource {

    private Nota nota;
    private INotaService notaService;

    @Inject
    public NotaResource(@Named("notaservicedefault") INotaService notaService){
        this.notaService = notaService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Listar Notas", description = "Recuperar uma lista completa de notas com todos os dados")
    public Response recuperar() {
        List<Nota> listaNotas = new ArrayList<Nota>();
        try {
            listaNotas = notaService.listar();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type("text/plain")
                    .build();
        }
        return Response.ok(listaNotas, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Path("{id}")
    @Operation(summary = "Recuperar Nota", description = "Recuperar apenas um nota a partir de seu id")
    public Response recuperar(@PathParam("id") Long id) {
        Nota nota;
        try {
            nota = notaService.recuperar(id);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type("text/plain")
                    .build();
        }
        return Response.ok(nota, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Criar Notas", description = "Cria uma nota completa")
    public Response criar(@Valid Nota nota) {
        Long id;
        try {
            id = notaService.criar(nota);
            nota.setId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type("text/plain")
                    .build();
        }
        return Response
                .status(Response.Status.CREATED).entity(nota).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Notas",
        description = "Cria uma nota completa"
    )
    public Response atualizar(@PathParam("id")Long id,Nota nota) {
       try{
           nota = notaService.atualizar(id, nota);
       }catch(Exception e){
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type("text/plain").build();
       }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Deletar uma Nota", description = "Deletar uma Nota a partir de seu id")
    public Response deletar(@PathParam("id") Long id) {
        try{
           notaService.deletar(id);
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type("text/plain").build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
