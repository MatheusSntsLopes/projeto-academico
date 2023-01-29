package br.com.academico.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import br.com.academico.aluno.AlunoResource;
import br.com.academico.disciplina.DisciplinaResource;
import br.com.academico.endereco.EnderecoResource;
import br.com.academico.exception.AcademicoExceptionMapper;
import br.com.academico.professor.ProfessorResource;
import br.com.academico.sala.SalaResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
 
@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Acadêmico Web API",
        version = "1.0",
        description = "Web API utilizando o estilo arquitetural REST"
    ),
    servers = {
        @Server(
            description = "Densenvolvimento",
            url = "/academico-web-api"
        )
    }
)
public class AcademicoResourceConfig extends ResourceConfig{
    
    public AcademicoResourceConfig(){
         registrarEndPoints();
         configurarSwagger();
         configurarValidacao();
         configurarInversaoControle();
    }

    private void registrarEndPoints(){
        System.out.println("[Configurando as classes resources/endpoints da aplicação]");
        register(EnderecoResource.class);
        register(SalaResource.class);
        register(DisciplinaResource.class);
        register(ProfessorResource.class);
        register(AlunoResource.class);
    }

    private void configurarSwagger(){
        System.out.println("Configurando o  Swagger || Open API");
        OpenApiResource openapiresource = new OpenApiResource();
        register(openapiresource);
    }

    private void configurarValidacao(){
        System.out.println("Configurando a Validação");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(AcademicoExceptionMapper.class);
    }

    private void configurarInversaoControle(){
        System.out.println("[Configurando a Inversão de Control (IOC)]");
        register(AutoScanIoFeature.class);
    }
}