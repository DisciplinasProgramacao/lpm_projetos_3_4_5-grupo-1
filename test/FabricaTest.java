import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class FabricaTest {

    private Cliente cliente;
    private Serie serie;
    private Filme filme;

    @Test
    public void fabricaClienteCriar() {
        Fabrica fabricaCliente = new FabricaCliente();
        String[] infoCliente = {"Cliente 3", "login3", "senha3"};
        cliente = (Cliente) fabricaCliente.criar(infoCliente);
        assertEquals("Cliente 3", cliente.getNome());
        assertEquals("login3", cliente.getLogin());
    }

    @Test
    public void fabricaSerieCriar() {
        Fabrica fabricaSerie = new FabricaSerie();
        String[] infoSerie = {"3", "Serie 3", "01/01/2003"};
        serie = (Serie) fabricaSerie.criar(infoSerie);
        assertEquals("Serie 3", serie.getNome());
    }

    @Test
    public void fabricaFilmeCriar() {
        Fabrica fabricaFilme = new FabricaFilme();
        String[] infoFilme = {"3", "Filme 3", "01/01/2003", "120"};
        filme = (Filme) fabricaFilme.criar(infoFilme);
        assertEquals("Filme 3", filme.getNome());
    }
}
