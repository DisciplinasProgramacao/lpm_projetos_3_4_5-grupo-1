/**
 * Interface Mídia, permite uniformizar o acesso aos dados de um tipo de mídia.
 */
public interface IMidia {

    // /** Array de strings que armazena os generos da mídia */
    // String[] GENEROS = { "Acao", "Aventura", "Comédia", "Drama", "Ficcao Científica", "Romance",
    //         "Suspense", "Terror" };

    // /** Array de strings que armazena os idiomas da mídia */
    // String[] IDIOMAS = { "Português", "Inglês", "Espanhol", "Francês", "Alemão", "Italiano" };

    /**
     * Registra uma avaliacao da mídia
     * 
     * @param avaliacao avaliacao da mídia
     */
    public void registrarAvaliacao(int avaliacao);

    /**
     * Adiciona uma audiencia a série
     */
    public void registrarAudiencia();

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     * 
     * @return String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    public String toFile();

    /**
     * Retorna o ID da mídia
     * 
     * @return ID da mídia
     */
    public int getID();

    /**
     * Retorna o genero da mídia
     * 
     * @return genero da mídia
     */
    public String getGenero();

    /**
     * Retorna o idioma da mídia
     * 
     * @return idioma da mídia
     */
    public String getIdioma();

    /**
     * Retorna a quantidade de episodios da serie, em caso de filme retorna -1
     * 
     * @return quantidade de episodios
     */
    public int getQntEp();

    /**
     * Retorna a duracao do filme, em caso de serie retorna -1
     * 
     * @return duracao do filme
     */
    public int getDuracao();

    /**
     * Retorna o nome da mídia
     * 
     * @return nome da mídia
     */
    public String getNome();

}
