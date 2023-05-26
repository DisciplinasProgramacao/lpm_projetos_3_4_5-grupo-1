import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Classe que representa um cliente do sistema da plataforma de streaming
 */
public interface ICliente {

    /**
     * Adiciona uma midia na lista de series para ver
     * 
     * @param midia a ser adicionada
     */
    public void adicionarNaLista(IMidia midia);

    /**
     * Adiciona uma midia na lista de series ja vistas. Retorna o mesmo cliente,
     * salvo se este se tornar um cliente especialista
     * 
     * @param midia     a ser adicionada
     * @param avaliacao avaliacao da midia
     * @param data      data em que a midia foi assistida
     * @return cliente atualizado
     */
    public ICliente registrarAudiencia(IMidia midia, int avaliacao, LocalDate data);

    /**
     * Remove uma midia da lista de series para ver
     * 
     * @param nomeMidia nome da midia a ser removida
     */
    public void retirarDaLista(String nomeMidia);

    /**
     * Filtra a lista de series para ver por genero
     * 
     * @param genero a ser filtrado
     * @return stream de series filtrada
     */
    public Stream<String> filtrarPorGenero(String genero);

    /**
     * Filtra a lista de series para ver por idioma
     * 
     * @param idioma a ser filtrado
     * @return stream de series filtrada
     */
    public Stream<String> filtrarPorIdioma(String idioma);

    /**
     * Filtra a lista de series para ver por quantidade de episodios
     * 
     * @param qntsEpisodios quantidade de episodios a ser filtrada
     * @return stream de series filtrada
     */
    public Stream<String> filtrarPorQntEpisodios(int qntsEpisodios);

    /**
     * Filtra a lista de filmes para ver por duracao
     * 
     * @param duracao a ser filtrada
     * @return stream de filmes filtrada
     */
    public Stream<String> filtrarPorDuracao(int duracao);

    /**
     * Verifica se o cliente inseriu a senha correta
     * 
     * @param senha a ser verificada
     * @return TRUE se a senha esta correta, FALSE caso contrario
     */
    public boolean loginPassword(String senha);

    /**
     * Retorna um array de Strings com as audiencias do cliente, a primeira parte do
     * array para series a ver e a segunda para series ja vistas
     *
     * Audiencia e armazenada da forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * series a assistir futuramente e “A” para series ja assistidas.
     * 
     * @return array de Strings com todas as audiencias do cliente
     */
    public String[] audiencias();

    /**
     * Retorna o nome de usuario do cliente
     * 
     * @return nome de usuario do cliente
     */
    public String getLogin();

    /**
     * Retorna a avaliacao de uma midia
     * 
     * @param idMidia id da midia a ser buscada
     */
    public int getAvaliacao(int idMidia);

}
