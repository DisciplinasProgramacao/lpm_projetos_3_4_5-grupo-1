import java.io.IOException;
import java.util.stream.Stream;

/**
 * Interface para a fabrica de Filmes, Series e Clientes.
 */
public interface Fabrica {
    /**
     * Cria um objeto a partir de um array de strings
     * 
     * @param info array de strings com as informações do objeto vindas do arquivo
     * @return objeto instanciado
     */
    public Object criar(String info[]);

    /**
     * Escreve as informações do objeto no arquivo
     * 
     * @param obj objeto a ser escrito no arquivo
     */
    public void escrever(Stream<Object> obj) throws IOException;
}
