package br.com.academico.nota;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import org.junit.Test;

import br.com.academico.config.AutoScanIoFeature;
import br.com.academico.exception.AcademicoExceptionMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;

import java.util.List;

import javax.json.Json;
import javax.validation.constraints.Null;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NotaResourceTest extends JerseyTest {

    private INotaService notaServiceMocked;

    @Override
    protected Application configure() {
        notaServiceMocked = mock(INotaService.class);
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig().
         property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true).
         register(AcademicoExceptionMapper.class).
         register(new NotaResource(notaServiceMocked));
    }

    @Test
    public void teste_recuperar_lista_notas() {
        //GIVEN - Dada alguma(s) pré-condição (arrange)
        List<Nota> listNotaEsperada;
        listNotaEsperada = new ArrayList<Nota>();
        listNotaEsperada.add(new Nota(6,1));
        listNotaEsperada.add(new Nota(4,1));

        given(notaServiceMocked.listar()).willReturn(listNotaEsperada);

         //WHEN - Quando ocorrer uma ação (act)
         Response response = target("/notas").request().get();
         List<Nota> listNotaResposta = response.readEntity(new GenericType<List<Nota>>() {});

          //THEN - Verifique a saída (assert)
        assertThat(response.getStatus())
        .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
        .isEqualTo(Status.OK.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(listNotaResposta)
            .withFailMessage("As Listas devem ter o mesmo tamanho")
            .hasSameSizeAs(listNotaEsperada);
    }

    @Test
    public void teste_recuperar_nota_por_id() {
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 1L;
        Nota notaEsperado = new Nota(8,2);
        notaEsperado.setId(idNota);

        given(notaServiceMocked.recuperar(idNota)).willReturn(notaEsperado);

        //WHEN - Quando ocorrer uma ação (act)

        Response response = target("/notas/{id}")
            .resolveTemplate("id", idNota)
            .request().get();

        Nota notaResposta = response.readEntity(new GenericType<Nota>() {});

        //THEN - Verifique a saída (assert)

        assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
             .isEqualTo(Status.OK.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
             .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(notaResposta)
             .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Nota")
             .isInstanceOf(Nota.class);
    }

    @Test
    public void teste_criar_nota(){
         //GIVEN - Dada alguma(s) pré-condição (arrange)

       Long idNota = 1L;
       Nota notaEnviado = new Nota(4, 1);
       notaEnviado.setId(idNota);

       given(notaServiceMocked.criar(notaEnviado)).willReturn(idNota);

       //WHEN - Quando ocorrer uma ação (act)

       Response response = target("/notas").request().post(Entity.json(notaEnviado));

       Nota notaSalvo = response.readEntity(new GenericType<Nota>() {});

       //THEN - Verifique a saída (assert)

       assertThat(response.getStatus())
           .withFailMessage("O codigo de status HTTP da resposta deve ser 201")
           .isEqualTo(Status.CREATED.getStatusCode());  

       assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
           .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
           .isEqualTo(MediaType.APPLICATION_JSON);

       assertThat(notaSalvo)
           .withFailMessage("A Nota salva não pode ser nullo")
           .isNotNull();   

       assertThat(notaSalvo)
           .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Nota")
           .isInstanceOf(Nota.class);

    }


    @Test
    public void teste_atualizar_nota_id(){
        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 1L;
        Nota notaEnviado = new Nota(4, 3);
        notaEnviado.setId(idNota);
 
        given(notaServiceMocked.atualizar(idNota, notaEnviado)).willReturn(notaEnviado);
 
        //WHEN - Quando ocorrer uma ação (act)
 
        Response response = target("/notas/{id}")
            .resolveTemplate("id", idNota)
            .request()
            .put(Entity.json(notaEnviado));
 
        //THEN - Verifique a saída (assert)
 
        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void teste_excluir_endereco_id(){
        
         //GIVEN - Dada alguma(s) pré-condição (arrange)

         Long idNota = 1L;

         given(notaServiceMocked.deletar(idNota)).willReturn(idNota);
 
         //WHEN - Quando ocorrer uma ação (act)
 
         Response response = target("/notas/{id}")
             .resolveTemplate("id", idNota)
             .request()
             .delete();
 
         //THEN - Verifique a saída (assert)
         assertThat(response.getStatus())
         .withFailMessage("codigo de status HTTP da resposta deve ser 204")
         .isEqualTo(Status.NO_CONTENT.getStatusCode());
    }
}
