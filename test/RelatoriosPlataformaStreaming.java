import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Testes para metodos de relatórios da plataforma streaming.
 */
public class RelatoriosPlataformaStreaming {

    private static PlataformaStreaming plataforma = PlataformaStreaming.getInstance();
    private static int MIDIAS_COMUM = 5;

    @BeforeAll
    public static void setUpAll() {
    }

    @BeforeEach
    public void setUp() {
        PlataformaStreaming.reset(); // reseta a plataforma para evitar problemas com testes anteriores
        // adição de clientes teste
        plataforma.adicionarCliente(new Cliente("John Doe", "john", "password"));
        plataforma.adicionarCliente(new Cliente("Alice Johnson", "alice", "password"));
        plataforma.adicionarCliente(new Cliente("Jane Smith", "jane", "password"));
        for (int i = 1; i <= 200; i++) // adição de 200 mídias
            plataforma.adicionarMidia(new Filme(i, "Filme " + i, LocalDate.now(), 120));
    }

    @Test
    public void testClienteViciado() {
        plataforma.login("john", "password", false);
        for (int i = 1; i <= MIDIAS_COMUM; i++) // adiciona audiência até o limite de 5 mídias, evitando que o cliente
                                                // se torne especialista
            plataforma.registrarAudiencia(true, plataforma.buscarMidia(i));
        assertEquals("John Doe " + MIDIAS_COMUM, plataforma.clienteViciado());
    }

    @Test
    public void testMaiorAvaliador() {
        plataforma.login("jane", "password", false);
        for (int i = 1; i <= MIDIAS_COMUM; i++) { // adiciona audiência até o limite de 5 mídias, evitando que o cliente
                                                  // se torne especialista
            plataforma.registrarAudiencia(true, plataforma.buscarMidia(i));
            plataforma.registrarAvaliacao(plataforma.buscarMidia(i), 4);
            System.out.println(plataforma.buscarMidia(i));
        }
        assertEquals("Jane Smith " + MIDIAS_COMUM, plataforma.clienteViciado());
        assertEquals("Jane Smith " + MIDIAS_COMUM, plataforma.maiorAvaliador());
    }

    @Test
    public void testClientesCom15Avaliacoes() {
        plataforma.login("alice", "password", false);
        Avaliacoes av = plataforma.getClienteAtual().get().avaliacoes();
        for (int i = 1; i <= 30; i++)
            av.avaliar(i, 4);
        assertEquals(1.0 / 3, plataforma.clientesCom15Avaliacoes());
    }

    /**
     * Teste para o método melhoresAvaliacoes().
     */
    @Test
    public void testMelhoresAvaliacoes() {
        Midia midia = plataforma.buscarMidia(1),
                midia2 = plataforma.buscarMidia(2);
        for (int i = 0; i <= 101; i++) { // adiciona avaliações para as duas primeiras mídias com dois clientes
            midia.registrarAvaliacao(5);
            midia2.registrarAvaliacao(4);
        }

        String melhoresAvaliacoes = plataforma.melhoresAvaliacoes() // pega as melhores avaliações, como só há duas
                                                                    // midias avaliadas, deve retornar apenas elas
                .map(a -> a.substring(5, 6)) // pega apenas o id da mídia
                .collect(Collectors.joining("\n")); // junta os ids em uma string para teste separando por \n

        assertEquals("2\n1", melhoresAvaliacoes); // as duas primeiras mídias devem ser as melhores avaliadas
    }

    @Test
    public void testMaisVisualizadas() {
        Midia midia = plataforma.buscarMidia(11),
                midia2 = plataforma.buscarMidia(5);
        for (int i = 0; i <= 101; i++) {
            midia.registrarAudiencia();
            midia2.registrarAudiencia();
        }
            midia.registrarAudiencia();

        String maisVisualizadas = plataforma.maisVisualizadas()
                .map(a -> a.substring(5, 7))
                .collect(Collectors.joining("\n"));

        assertEquals("11\n5 \n1 \n2 \n3 \n4 \n6 \n7 \n8 \n9 ", maisVisualizadas);
    }

}