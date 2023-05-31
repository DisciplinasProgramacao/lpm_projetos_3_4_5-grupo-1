import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.stream.Stream;

class PlataformaStreamingTest {

    PlataformaStreaming plataforma;
    Cliente cliente;
    Filme filme;
    Serie serie;

    @BeforeEach
    void setUp() {
        plataforma = new PlataformaStreaming("Netflix");
        cliente = new Cliente("username", "login", "password");
        filme = new Filme(1, "Filme teste", LocalDate.now(), 120);
        serie = new Serie(2, "Series 1", LocalDate.now());
    }

    void efetuaLogin() {
        plataforma.login("login", "password", false);
    }

    void adicionaMidia() {
        plataforma.adicionarMidia(filme);
        plataforma.adicionarMidia(serie);
    }

    void adicionaCliente() {
        plataforma.adicionarCliente(cliente);
    }

    @Test
    void testAdicionarMidia() {
        adicionaMidia();

        assertTrue(plataforma.getMidia().containsValue(filme));
        assertTrue(plataforma.getMidia().containsValue(serie));
    }

    @Test
    void testAdicionarCliente() {
        adicionaCliente();

        assertTrue(plataforma.getClientes().containsValue(cliente));
    }

    @Test
    void testLogin() {
        adicionaCliente();

        efetuaLogin();
        assertTrue(plataforma.getClienteAtual().isPresent());
        assertEquals(cliente, plataforma.getClienteAtual().get());

        plataforma.logOff();
        assertFalse(plataforma.getClienteAtual().isPresent());

        plataforma.login("login", "wrongPassword", false);
        assertFalse(plataforma.getClienteAtual().isPresent());
    }

    @Test
    void testLogOff() {
        adicionaCliente();
        efetuaLogin();

        plataforma.logOff();
        assertFalse(plataforma.getClienteAtual().isPresent());
    }

    @Test
    void testFiltrarPorGenero() {
        adicionaMidia();
        adicionaCliente();
        efetuaLogin();

        Stream<String> midiasDoGenero = plataforma.filtrarPorGenero("Ação");
        assertFalse(midiasDoGenero.anyMatch(midia -> midia.equals(filme.toString())));

        cliente.adicionarNaLista(filme);
        midiasDoGenero = plataforma.filtrarPorGenero("Ação");
        assertFalse(midiasDoGenero.anyMatch(midia -> midia.equals(filme.toString())));
    }

    @Test
    void testFiltrarPorIdioma() {
        adicionaMidia();
        
        adicionaCliente();
        efetuaLogin();
    
        Stream<String> midiasDoIdioma = plataforma.filtrarPorIdioma("Inglês");
        assertFalse(midiasDoIdioma.anyMatch(midia -> midia.equals(filme.toString())));
    
        cliente.adicionarNaLista(filme);
        midiasDoIdioma = plataforma.filtrarPorIdioma("Inglês");
        assertFalse(midiasDoIdioma.anyMatch(midia -> midia.equals(filme.toString())));
    }
    
    @Test
    void testFiltrarPorQntEpisodios() {
        adicionaMidia();
        Stream<String> midiasPorQntEpisodios = plataforma.filtrarPorQntEpisodios(3);
        assertFalse(midiasPorQntEpisodios.anyMatch(midia -> midia.equals(serie.toString())));
    
        midiasPorQntEpisodios = plataforma.filtrarPorQntEpisodios(10);
        assertTrue(midiasPorQntEpisodios.anyMatch(midia -> midia.equals(serie.toString())));
    }
    
    @Test
    void testFiltrarPorDuracao() {
        adicionaMidia();
    
        Stream<String> midiasPorDuracao = plataforma.filtrarPorDuracao(90);
        assertFalse(midiasPorDuracao.anyMatch(midia -> midia.equals(filme.toString())));
    
        midiasPorDuracao = plataforma.filtrarPorDuracao(120);
        assertTrue(midiasPorDuracao.anyMatch(midia -> midia.equals(filme.toString())));
    }
    
    @Test
    void testBuscarMidia() {
        adicionaMidia();
        
    
        assertEquals(filme.toString(), plataforma.buscarMidia("Filme teste"));
        assertEquals(serie.toString(), plataforma.buscarMidia("Series 1"));
        assertEquals(plataforma.buscarMidia("Midia Inexistente"), "null");
    }
    
    @Test
    void testToString() {
        adicionaMidia();
        adicionaCliente();
        assertEquals(" Ha " + plataforma.getMidia().size() + " midias cadastradas na plataforma Netflix e " + plataforma.getClientes().size() + " clientes cadastrados.", plataforma.toString());
    }
    
    // Outros testes...
    
}
    