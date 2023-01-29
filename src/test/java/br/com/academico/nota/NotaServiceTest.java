package br.com.academico.nota;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class NotaServiceTest {

    private INotaRepository notaRepositoryMocked;

    private NotaService notaService;

    @Before
    public void init() {
        notaRepositoryMocked = mock(INotaRepository.class);
        notaService = new NotaService(notaRepositoryMocked);
    }

    @Test
    public void teste_recuperar_lista_notas() {
        List<Nota> listNotaEsperada;
        listNotaEsperada = new ArrayList<Nota>();
        listNotaEsperada.add(new Nota(1,3));
        listNotaEsperada.add(new Nota(3,2));

        given(notaRepositoryMocked.findAll()).willReturn(listNotaEsperada);

        List<Nota> listNotaResposta = notaService.listar();

        assertThat(listNotaResposta).withFailMessage("O retorno do metodo listar deve ser uma lista de notas")
                .isInstanceOf(List.class);

        assertThat(listNotaResposta)
                .withFailMessage("O retorno do metodo listar deve ser uma lista de nota não nula").isNotNull();

        assertThat(listNotaResposta.size())
                .withFailMessage("O retorno do metodo listar deve ser uma lista com duas notas").isEqualTo(2);
    }

    @Test
    public void teste_recuperar_nota_por_id() {

        // GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 1L;
        Nota notaEsperado = new Nota(4,2);
        notaEsperado.setId(idNota);

        given(notaRepositoryMocked.getById(idNota)).willReturn(Optional.of(notaEsperado));

        // WHEN - Quando ocorrer uma ação (act)

        Nota notaResposta = notaService.recuperar(idNota);

        // THEN - Verifique a saída (assert)

        assertThat(notaResposta)
                .withFailMessage("O retorno do método recuperar não pode ser nullo")
                .isNotNull();

        assertThat(notaResposta)
                .withFailMessage("O retorno do método recuperar deve ser um objeto Nota")
                .isInstanceOf(Nota.class);
    }

    @Test
    public void teste_criar_nota() {
        // GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 10L;
        Nota notaEnviado = new Nota(4,5);
        notaEnviado.setId(idNota);

        given(notaRepositoryMocked.save(notaEnviado)).willReturn(notaEnviado);

        // WHEN - Quando ocorrer uma ação (act)

        Long idNotaSalvo = notaService.criar(notaEnviado);

        // THEN - Verifique a saída (assert)

        assertThat(idNotaSalvo)
                .withFailMessage("O retorno do método criar não pode ser um ID nullo")
                .isNotNull();

        assertThat(idNotaSalvo)
                .withFailMessage("O retorno do método criar deve ser um ID de um Nota criado")
                .isInstanceOf(Long.class);

    }

    @Test
    public void teste_atualizar_nota_por_id() {

        // GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 10L;
        Nota notaEnviado = new Nota(9,1);
        notaEnviado.setId(idNota);

        given(notaRepositoryMocked.getById(notaEnviado.getId()))
                .willReturn(Optional.of(notaEnviado));

        given(notaRepositoryMocked.update(notaEnviado))
                .willAnswer(invocation -> invocation.getArgument(0));

        // WHEN - Quando ocorrer uma ação (act)

        Nota notaAtualizado = notaService.atualizar(notaEnviado.getId(), notaEnviado);

        // THEN - Verifique a saída (assert)

        assertThat(notaAtualizado)
                .withFailMessage("O retorno do método atualizar não pode ser uma Nota nullo")
                .isNotNull();

        assertThat(notaAtualizado)
                .withFailMessage("O retorno do método atualizar deve ser um objeto Nota")
                .isInstanceOf(Nota.class);
    }

    @Test
    public void teste_deletar_nota_por_id() {
        // GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 10L;
        Nota notaEnviado = new Nota(3,2);
        notaEnviado.setId(idNota);

        given(notaRepositoryMocked.getById(idNota))
                .willReturn(Optional.of(notaEnviado));

        willDoNothing().given(notaRepositoryMocked).delete(idNota);

        // WHEN - Quando ocorrer uma ação (act)

        Long idNotaDeletado = notaService.deletar(idNota);

        // THEN - Verifique a saída (assert)

        assertThat(idNotaDeletado)
                .withFailMessage("O retorno do método deletar não pode ser um ID nullo")
                .isNotNull();

        assertThat(idNotaDeletado)
                .withFailMessage("O retorno do método deletar deve ser um ID de uma Nota deletada")
                .isInstanceOf(Long.class);

    }

    @Test
    public void teste_recuperar_nota_por_id_inexistente() {
        // GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 10L;
        Nota notaEsperado = new Nota(4,2);
        notaEsperado.setId(idNota);

        given(notaRepositoryMocked.getById(notaEsperado.getId()))
                .willReturn(Optional.empty());

        // WHEN - Quando ocorrer uma ação (act)

        Exception exception = assertThrows(NotaNaoExisteException.class, () -> {
            Nota nota = notaService.recuperar(999L);
        });

        // THEN - Verifique a saída (assert)

        assertThat(exception.getMessage())
                .withFailMessage("Menssagem de execeção deve ser lançada e ser compatível com a esperada")
                .contains("A nota não existe na base de dados.");

    }

    @Test
    public void teste_atualizar_nota_por_id_inexistente() {
        // GIVEN - Dada alguma(s) pré-condição (arrange)

        Long idNota = 10L;
        Nota notaEnviado = new Nota(4,2);
        notaEnviado.setId(idNota);

        given(notaRepositoryMocked.getById(notaEnviado.getId()))
                .willReturn(Optional.empty());

        // WHEN - Quando ocorrer uma ação (act)

        Exception exception = assertThrows(NotaNaoExisteException.class, () -> {
            notaService.atualizar(notaEnviado.getId(), notaEnviado);
        });

        // THEN - Verifique a saída (assert)

        assertThat(exception.getMessage())
                .withFailMessage("Mensagem de execeção deve ser lançada e ser compatível com a esperada")
                .contains("A nota não existe na base de dados.");
    }

}
