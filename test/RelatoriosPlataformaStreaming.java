import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * Testes para metodos de relatórios da plataforma streaming.
 */
public class RelatoriosPlataformaStreaming {

    private static PlataformaStreaming plataforma;

    @BeforeAll
    public static void setUpAll() {
        plataforma = PlataformaStreaming.getInstance();
        plataforma.adicionarCliente(new Cliente("John Doe", "john", "password"));
        plataforma.adicionarCliente(new Cliente("Jane Smith", "jane", "password"));
        plataforma.adicionarCliente(new Cliente("Alice Johnson", "alice", "password"));
        for (int i = 1; i <= 30; i++)
            plataforma.adicionarMidia(new Filme(i, "Filme " + i, LocalDate.now(), 120));
    }

    @Test
    public void testClienteViciado() {
        plataforma.login("john", "password", false);
        for (int i = 1; i <= 30; i++)
            plataforma.registrarAudiencia(true, plataforma.buscarMidia(i));
        assertEquals("John Doe", plataforma.clienteViciado().toString().split(";")[0]);
    }

    @Test
    public void testMaiorAvaliador() {
        plataforma.login("jane", "password", false);
        for (int i = 1; i <= 30; i++) {
            plataforma.registrarAudiencia(true, plataforma.buscarMidia(i));
            plataforma.registrarAvaliacao(plataforma.buscarMidia(i), 4);
        }
        assertEquals("Jane Smith", plataforma.maiorAvaliador().toString().split(";")[0]);
    }

    @Test
    public void testClientesCom15Avaliacoes() {
        plataforma.login("alice", "password", false);
        for (int i = 1; i <= 26; i++) {
            plataforma.registrarAudiencia(true, plataforma.buscarMidia(i));
            plataforma.registrarAvaliacao(plataforma.buscarMidia(i), 3);
        }
        plataforma.logOff();
         //assertEquals(1, plataforma.clientesCom15Avaliacoes());
    }

    /**
     * Teste para o método melhoresAvaliacoes().
     */
    @Test
    public void testMelhoresAvaliacoes() {

        //plataforma.registrarAvaliacao(plataforma.buscarMidia(1), 4);
        //plataforma.registrarAvaliacao(plataforma.buscarMidia(2), 5);
        //plataforma.registrarAvaliacao(plataforma.buscarMidia(3), 3);

        //Optional<String> melhoresAvaliacoes = plataforma.melhoresAvaliacoes().findFirst();
        // assertTrue(melhoresAvaliacoes.isPresent());
        // assertEquals("1;Filme 1;01/01/2021;120;AÇÃO;0;0;0", melhoresAvaliacoes);
    }

    @Test
    public void testMaisVisualizadas() {

    }

}