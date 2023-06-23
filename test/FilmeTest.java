import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FilmeTest {

    private Filme filme;

    @BeforeEach
    public void setup() {
        int id = 1;
        String nome = "Filme Teste";
        LocalDate dataLancamento = LocalDate.of(2021, 1, 1);
        int duracao = 120;

        filme = new Filme(id, nome, dataLancamento, duracao);
    }

    @Test
    public void testRegistrarAvaliacao() {
        filme.registrarAvaliacao(5);
        Assertions.assertEquals(5, filme.getRatingMedio());
        Assertions.assertEquals(1, filme.getQntAvaliacoes());

        filme.registrarAvaliacao(4);
        Assertions.assertEquals(4, filme.getRatingMedio());
        Assertions.assertEquals(2, filme.getQntAvaliacoes());
    }

    @Test
    public void testRegistrarAudiencia() {
        filme.registrarAudiencia();
        Assertions.assertEquals(1, filme.getAudiencia());

        filme.registrarAudiencia();
        Assertions.assertEquals(2, filme.getAudiencia());
    }

    @Test
    public void testToFile() {
        String expected = "1;Filme Teste;01/01/2021;120";
        String actual = filme.toFile();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        String expected = " id: 1 | Nome: Filme Teste | Genero: " + filme.getGenero() +
                " | Data de Lancamento: 2021-01-01 | Audiencia: 0| Avaliacoes: 0 | Rating: 0| Duracao: 120 minutos| Idioma: " + filme.getIdioma() +".";
        String actual = filme.toString();
        Assertions.assertEquals(expected, actual);
    }
}
