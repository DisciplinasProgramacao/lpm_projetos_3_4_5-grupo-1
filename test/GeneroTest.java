import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * Testes da classe Genero.
 */
public class GeneroTest {
    
    @Test
    public void testGetNome() {
        Genero genero = Genero.AÇÃO;
        assertEquals("AÇÃO", genero.getNome());
    }

    @Test
    public void testGetGeneros() {
        String generos = Genero.getGeneros();
        boolean acerto = false;
        for (Genero genero : Genero.values()) {
            if (generos.contains(genero.getNome())) {
                acerto = true;
            }
        }
        assertTrue(acerto);
    }

    @Test
    public void testPesquisaGenero() {
        Genero genero = Genero.pesquisaGenero("COMÉDIA");
        assertEquals(Genero.COMÉDIA, genero);
    }

    @Test
    public void testSortearGenero() {
        Genero genero = Genero.sortearGenero();
        assertNotNull(genero);
    }

}