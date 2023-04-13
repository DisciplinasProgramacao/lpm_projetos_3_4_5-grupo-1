import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class Testes {
    Serie serie;

    @BeforeEach
    public void setUp() {
        serie = new Serie(new String[] { "Ação", "Aventura" }, "The Flash", "Inglês", 23);
    }

    @Test
    public void registrarAudienciaTest() {
        serie.registrarAudiencia();
        assertEquals(1, serie.getAudiencia());
    }
}
