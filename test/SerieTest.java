import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SerieTest {

    private Serie serie;

    @BeforeEach
    void setUp() {
        int id = 1;
        String nome = "Série de Teste";
        LocalDate dataLancamento = LocalDate.of(2020, 1, 1);
        serie = new Serie(id, nome, dataLancamento);
    }

    @Test
    void registrarAvaliacao_deveAtualizarRatingMedio() {
        // Arrange
        int avaliacao = 5;

        // Act
        serie.registrarAvaliacao(5);

        // Assert
        Assertions.assertEquals(avaliacao, serie.getRatingMedio());
    }

    @Test
    void registrarAudiencia_deveIncrementarAudiencia() {
        // Arrange
        int audienciaAnterior = serie.getAudiencia();

        // Act
        serie.registrarAudiencia();

        // Assert
        Assertions.assertEquals(audienciaAnterior + 1, serie.getAudiencia());
    }

    @Test
    void toFile_deveRetornarStringFormatadaCorretamente() {
        // Arrange
        String id = "1";
        String nome = "Série de Teste";
        String dataLancamento = "01/01/2020";
        String expected = id + ";" + nome + ";" + dataLancamento;

        // Act
        String result = serie.toFile();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void toString_deveRetornarStringComInformacoesCorretas() {
        // Arrange
        String id = "1";
        String nome = "Série de Teste";
        String genero = serie.getGenero();
        String dataLancamento = "2020-01-01";
        int audiencia = serie.getAudiencia();
        int qntEp = serie.getQntEp();
        String expected = " id: " + id + " | Nome: " + nome + " | Genero: " + genero + " | Data de Lancamento: "
                + dataLancamento + " | Audiencia: " + audiencia + "| Avaliacoes: 0 | Rating: 0 " + "| Quantidade de episodios: " + qntEp + " |";

        // Act
        String result = serie.toString();

        // Assert
        Assertions.assertEquals(expected, result);
    }
}
