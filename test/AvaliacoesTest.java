import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Testes unitários para a classe Avaliacoes, AvaliacaoComum e AvaliacaoEspecialista.
 */
public class AvaliacoesTest {
    
    @Test
    public void testAvaliacoes() {
        Avaliacoes avaliacoes = new Avaliacoes() {
            @Override
            public void avaliar(int idMidia, int avaliacao) {
                super.put(idMidia, new Comentario(avaliacao, ""));
            }
        };

        assertEquals(0, avaliacoes.total());
        assertFalse(avaliacoes.midiaAvaliada(1));
        assertEquals("Voce ainda nao avaliou esta midia", avaliacoes.get(1));
    }

    @Test
    public void testAvaliacaoComum() {
        AvaliacaoComum avaliacaoComum = new AvaliacaoComum();

        assertEquals(0, avaliacaoComum.total());
        assertFalse(avaliacaoComum.midiaAvaliada(1));
        assertEquals("Voce ainda nao avaliou esta midia", avaliacaoComum.get(1));
    }

    @Test
    public void testAvaliacaoEspecialista() {
        Avaliacoes avaliacaoEspecialista = new AvaliacaoComum().goNext();

        assertEquals(0, avaliacaoEspecialista.total());
        assertFalse(avaliacaoEspecialista.midiaAvaliada(1));
        assertEquals("Voce ainda nao avaliou esta midia", avaliacaoEspecialista.get(15));
    }

    @Test
    public void testAvaliar() {
        Avaliacoes avaliacoes = new Avaliacoes() {
            @Override
            public void avaliar(int idMidia, int avaliacao) {
                for (int i = 0; i < 10; i++)
                    super.put(i, new Comentario(avaliacao, "top"));
            }
        };
        avaliacoes.avaliar(1, 4);

        assertEquals(10, avaliacoes.total());

        for (int i = 0; i < 8; i++) {
            assertTrue(avaliacoes.midiaAvaliada(i));
        }
        assertEquals("4 top", avaliacoes.get(1));
    }

    @Test
    public void testAvaliarAvaliacaoComum() {
        AvaliacaoComum avaliacaoComum = new AvaliacaoComum(){
            @Override
            public void avaliar(int idMidia, int avaliacao) {
                super.put(idMidia, new Comentario(avaliacao, "paia"));
            }};

        avaliacaoComum.avaliar(1, 1);

        assertEquals(1, avaliacaoComum.total());
        assertTrue(avaliacaoComum.midiaAvaliada(1));
        assertEquals("1 paia", avaliacaoComum.get(1));
    }
}
