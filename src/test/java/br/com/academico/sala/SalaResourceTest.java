package br.com.academico.sala;

import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider.Text;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.TestProperties;

import javax.json.Json;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class SalaResourceTest extends JerseyTest {
    @Override
    protected Application configure(){
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(SalaResource.class);
    }

    @Test
    public void teste_recuperar_lista_sala(){

        Response response = target("/salas").request().get();

        List<Sala> listSalas = response.readEntity(new GenericType<List<Sala>>() {});

        assertEquals("O codigo de status http da resposta deve ser igual a 200", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo do conteudo da resposta http deve ser igual a um json", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteudo da resposta deve ser uma lista", listSalas instanceof List<?>);
    }

    @Test
    public void teste_criar_sala(){
        String salaJson = Json.createObjectBuilder().add("quadroBranco", true).add("arCondicionado", true).add("laboratorio", true).add("numeroSala", "22").add("capacidadeAluno", 22)
        .build().toString();

        Response response = target("/salas").request().post(Entity.json(salaJson));
        Sala sala = response.readEntity(new GenericType<Sala>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 201: ", Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Endereço: ", sala instanceof Sala);
    }

    @Test
    public void teste_atualizar_sala_id(){
        String enderecoJSON = Json.createObjectBuilder()
        .add("quadroBranco", true)
        .add("arCondicionado", false)
        .add("laboratorio", true)
        .add("numeroSala", "34")
        .add("capacidadeAluno", 40)
        .build()
        .toString();

        Response response = target("/salas/12").request().put(Entity.json(enderecoJSON));
        Sala sala = response.readEntity(new GenericType<Sala>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_excluir_endereco_id(){
        Response response = target("/salas/12").request().delete();

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }


}
