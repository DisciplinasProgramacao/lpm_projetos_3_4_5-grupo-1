import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

public class ClienteTest {
    private Cliente cliente;
    private Filme filme;
    private Serie serie;

    @BeforeEach
    public void setup() {
        cliente = new Cliente("username", "login", "password");
        filme = new Filme(1, "Filme teste", LocalDate.now(), 120);
        serie = new Serie(2, "Series 1", LocalDate.now());
    }

    @Test
    public void testAdicionarNaLista() {
        cliente.adicionarNaLista(filme);
        cliente.adicionarNaLista(serie);

        Assertions.assertEquals(2, cliente.getListaParaVer().size());
        Assertions.assertTrue(cliente.getListaParaVer().contains(filme));
        Assertions.assertTrue(cliente.getListaParaVer().contains(serie));
    }

    @Test
    public void testRegistrarAudienciaWithoutRating() {
        cliente.registrarAudiencia(filme, 0, LocalDate.now());

        Assertions.assertEquals(1, cliente.getListaJaVistas().size());
        Assertions.assertTrue(cliente.getListaJaVistas().contains(filme));
        Assertions.assertFalse(cliente.getAvaliacoes().containsKey(filme.getID()));
    }

    @Test
    public void testRegistrarAudienciaWithRating() {
        cliente.registrarAudiencia(filme, 4, LocalDate.now());

        Assertions.assertEquals(1, cliente.getListaJaVistas().size());
        Assertions.assertTrue(cliente.getListaJaVistas().contains(filme));
        Assertions.assertEquals(4, cliente.getAvaliacao(filme.getID()));
    }

    @Test
    public void testTornarEspecialista() {
        cliente.registrarAudiencia(filme, 4, LocalDate.now());
        cliente.registrarAudiencia(serie, 5, LocalDate.now());
        cliente.registrarAudiencia(filme, 3, LocalDate.now());
        cliente.registrarAudiencia(serie, 2, LocalDate.now());
        cliente.registrarAudiencia(filme, 4, LocalDate.now());

        Assertions.assertTrue(cliente instanceof Cliente);
        ClienteEspecialista especialista = cliente.tornarEspecialista();
        Assertions.assertTrue(especialista instanceof ClienteEspecialista);
    }

    @Test
    public void testRetirarDaLista() {
        cliente.adicionarNaLista(filme);
        cliente.adicionarNaLista(serie);

        cliente.retirarDaLista("Series 1");

        Assertions.assertEquals(1, cliente.getListaParaVer().size());
        Assertions.assertTrue(cliente.getListaParaVer().contains(filme));
        Assertions.assertFalse(cliente.getListaParaVer().contains(serie));
    }

    @Test
    public void testFiltrarPorGenero() {
        cliente.registrarAudiencia(filme, 4, LocalDate.now());
        cliente.registrarAudiencia(serie, 5, LocalDate.now());

        Stream<String> filteredStream = cliente.filtrarPorGenero(filme.getGenero());
        long count = filteredStream.count();

        Assertions.assertEquals(1, count);
    }

    @Test
    public void testFiltrarPorIdioma() {
        cliente.registrarAudiencia(filme, 4, LocalDate.now());
        cliente.registrarAudiencia(serie, 5, LocalDate.now());

        Stream<String> filteredStream = cliente.filtrarPorIdioma(serie.getIdioma());
        long count = filteredStream.count();

        Assertions.assertEquals(1, count);
    }

    @Test
    public void testFiltrarPorQntEpisodios() {
        cliente.registrarAudiencia(filme, 4, LocalDate.now());
        cliente.registrarAudiencia(serie, 5, LocalDate.now());

        Stream<String> filteredStream = cliente.filtrarPorQntEpisodios(serie.getQntEp());
        long count = filteredStream.count();

        Assertions.assertEquals(1, count);
    }

    @Test
    public void testFiltrarPorDuracao() {
        cliente.registrarAudiencia(filme, 4, LocalDate.now());
        cliente.registrarAudiencia(serie, 5, LocalDate.now());

        Stream<String> filteredStream = cliente.filtrarPorDuracao(120);
        long count = filteredStream.count();

        Assertions.assertEquals(1, count);
    }

    @Test
    public void testLoginPasswordCorrectPassword() {
        boolean result = cliente.loginPassword("password");

        Assertions.assertTrue(result);
    }

    @Test
    public void testLoginPasswordIncorrectPassword() {
        boolean result = cliente.loginPassword("incorrectpassword");

        Assertions.assertFalse(result);
    }

    @Test
    public void testAudiencias() {
        cliente.adicionarNaLista(filme);
        cliente.registrarAudiencia(filme, 4, LocalDate.now());
        cliente.registrarAudiencia(serie, 5, LocalDate.now());

        String[] audiencias = cliente.audiencias();

        Assertions.assertEquals(2, audiencias.length-1);
        Assertions.assertEquals("login;A;1", audiencias[0]);
        Assertions.assertEquals("login;F;1", audiencias[1]);
    }

    @Test
    public void testGetLogin() {
        String login = cliente.getLogin();

        Assertions.assertEquals("login", login);
    }

    @Test
    public void testGetAvaliacao() {
        cliente.registrarAudiencia(filme, 4, LocalDate.now());

        int avaliacao = cliente.getAvaliacao(1);

        Assertions.assertEquals(4, avaliacao);
    }
}

