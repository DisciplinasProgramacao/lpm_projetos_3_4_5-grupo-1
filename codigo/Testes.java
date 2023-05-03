import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

/**
 * Classe de testes unitários.
 */
public class Testes {

    private static Cliente cliente;
    private static Série série;
    private static Filme filme;

    /**
     * Inicializa os objetos a serem testados.
     */
    @BeforeAll
    public static void setUp() {
        cliente = new Cliente("João", "joao", "1234");
        série = new Série(1, "Ação", "Série 1", "Português", LocalDate.now(), 10);
        filme = new Filme(2, "Aventura", "Filme 1", "Português", LocalDate.now(), 120);
    }

    /**
     * Testa o construtor da classe Cliente.
     */
    @Test
    public void testarCliente() {
        assertEquals("João;joao;1234", cliente.toString());
    }

    /**
     * Testa o construtor da classe Série.
     */
    @Test
    public void testarSérie() {
        assertEquals(
                " ID: 1 | Nome: Série 1 | Gênero: Ação | Data de Lançamento: " + LocalDate.now() + " | Audiência: 0",
                série.toString() //
        );
    }

    /**
     * Testa o construtor da classe Filme.
     */
    @Test
    public void testarFilme() {
        assertEquals(
                " ID: 2 | Nome: Filme 1 | Gênero: Aventura | Data de Lançamento: " + LocalDate.now()
                        + " | Audiência: 0",
                filme.toString() //
        );
    }

    /**
     * Testa a adição de audiências.
     */
    @Test
    public void testarAudiência() {
        cliente = new Cliente("João", "joao", "1234");
        cliente.adicionarNaLista(série);
        assertEquals("joao;A;1", cliente.audiências()[0]);
    }

    /**
     * Testa a busca de filmes.
     */
    @Test
    public void testarBuscaFilme() {
        cliente.adicionarNaLista(filme);
        assertTrue(cliente.filtrarPorIdioma("Português").contains(filme));
    }

    /**
     * Testa a busca de séries.
     */
    @Test
    public void testarBuscaSérie() {
        cliente.adicionarNaLista(série);
        assertTrue(cliente.filtrarPorGênero("Ação").contains(série));
    }

    /**
     * Testa o login do cliente.
     */
    @Test
    public void testarLogin() {
        assertTrue(cliente.loginPassword("1234"));
    }

}
