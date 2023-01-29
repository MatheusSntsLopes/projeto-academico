package br.com.academico.nota;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class NotaRepositoryTest {

    private EntityManagerFactory emf;
    
    private EntityManager em;

    private INotaRepository notaRepositoryJPA;

    @Before
    public void init(){
        emf = Persistence.createEntityManagerFactory("academico-pu");
        em = emf.createEntityManager();
        notaRepositoryJPA = new NotaRepositoryJPA(em);
    }

    @Test
    public void teste_findAll_notas(){
        
        //WHEN - Quando ocorrer uma ação (act)

        List<Nota> listaNotasResposta = notaRepositoryJPA.findAll();

        //THEN - Verifique a saída (assert)

        assertThat(listaNotasResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de notas não nulla")
            .isNotNull();
        
        assertThat(listaNotasResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de notas")
            .isInstanceOf(List.class);

        assertThat(listaNotasResposta.size())
            .withFailMessage("O retorno do método listar deve ser uma lista de notas com a quantidade de notas no banco de dados")
            .isEqualTo(listaNotasResposta.size()); 
        
    }

    @Test
    public void teste_getById_notas(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Nota notaEsperado = new Nota(8, 0);
        notaRepositoryJPA.save(notaEsperado);
        
        //WHEN - Quando ocorrer uma ação (act)

        Optional<Nota> notaRecuperado = notaRepositoryJPA.getById(notaEsperado.getId());

        //THEN - Verifique a saída (assert)

        assertThat(notaRecuperado.get())
            .withFailMessage("O retorno do método getById não pode ser nullo")
            .isNotNull();

        assertThat(notaRecuperado.get())
            .withFailMessage("O retorno do método getById deve ser um objeto Nota")
            .isInstanceOf(Nota.class);

        assertThat(notaRecuperado.get().getId())
            .withFailMessage("A nota recuperado deve ter o mesmo ID da nota recuperada")
            .isEqualTo(notaEsperado.getId());    
        
    }

    @Test
    public void teste_save_notas(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Nota notaEnviado = new Nota(7, 3);
        
        //WHEN - Quando ocorrer uma ação (act)
        Nota notaSalvo = notaRepositoryJPA.save(notaEnviado);
        
        //THEN - Verifique a saída (assert)
        assertThat(notaSalvo)
            .withFailMessage("O retorno do método save não pode ser nullo")
            .isNotNull();

        assertThat(notaSalvo)
            .withFailMessage("O retorno do método save deve ser um objeto Nota")
            .isInstanceOf(Nota.class);
            
        assertThat(notaSalvo.getValor())
            .withFailMessage("O Valor da nota deve ser igual ao informado")
            .isEqualTo(7);
            
        assertThat(notaSalvo.getPeso())
            .withFailMessage("O Peso da nota deve ser igual ao informado")
            .isEqualTo(3);    
        
    }

    @Test
    public void teste_update_notas(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Nota notaEnviado = new Nota(8, 2);
        notaRepositoryJPA.save(notaEnviado);
        
        //WHEN - Quando ocorrer uma ação (act)
        notaEnviado.setValor(9);;
        notaEnviado.setPeso(3);;
        Nota notaAtualizado = notaRepositoryJPA.update(notaEnviado);

        //THEN - Verifique a saída (assert)
        assertThat(notaAtualizado)
            .withFailMessage("O retorno do método update não pode ser nullo")
            .isNotNull();
            
        assertThat(notaAtualizado.getValor())
            .withFailMessage("O Valor atualizado da nota deve ser igual ao informado")
            .isEqualTo(9);
            
        assertThat(notaAtualizado.getPeso())
            .withFailMessage("O Peso atualizado da nota deve ser igual ao informado")
            .isEqualTo(3);    
    
    }

    @Test
    public void teste_delete_notas(){

        //GIVEN - Dada alguma(s) pré-condição (arrange)

        Nota notaEnviado = new Nota(5, 1);
        notaRepositoryJPA.save(notaEnviado);
        
        //WHEN - Quando ocorrer uma ação (act)
        
        notaRepositoryJPA.delete(notaEnviado.getId());

        //THEN - Verifique a saída (assert)

        Optional<Nota> notaDeletado = notaRepositoryJPA.getById(notaEnviado.getId());

        assertThat(notaDeletado.isEmpty())
            .withFailMessage("O retorno do método delete deve ser nullo")
            .isEqualTo(true);     
        
    }
}