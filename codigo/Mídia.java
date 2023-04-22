import java.time.LocalDate;

/**
 * Classe Mídia, armazena os dados de uma mídia.
 */
abstract public class Mídia {

    /** Array de strings que armazena os gêneros da mídia */
    protected static final String[] GENÊROS = { "Ação", "Aventura", "Comédia", "Drama", "Ficção Científica", "Romance",
            "Suspense", "Terror" };

    /** Nome da mídia */
    protected String nome,
    /** Gênero da mídia */
    gênero,
    /** Idioma da mídia */
    idioma;

    /** Id da mídia */
    protected final int id;
    /** Total de audiência da mídia */
    protected int audiência;

    /** Data de lançamento da mídia */
    protected LocalDate dataLançamento; // @formatter:on

    /**
     * Construtor da classe Mídia
     * 
     * @param id     id da mídia
     * @param gênero gênero da mídia
     * @param nome   nome da mídia
     * @param idioma idioma da mídia
     * @param qntEp  quantidade de episodios da mídia
     */
    public Mídia(int id, String gênero, String nome, String idioma, LocalDate dataLançamento) {
        this.id = id;
        this.gênero = gênero;
        this.nome = nome;
        this.idioma = idioma;
        this.dataLançamento = dataLançamento;
        this.audiência = 0;
    }

    /** @formatter:off
     * Adiciona uma audiência a série
     */
    public void registrarAudiência() { audiência++; }
    /** Retorna o nome da mídia @return nome da mídia */
    public String getNome() { return this.nome; }
    /** Retorna o gênero da mídia @return gênero da mídia */
    public int getID() { return this.id; }
    /** Retorna o gênero da mídia @return gênero da mídia */
    public String getGênero() { return this.gênero; }
    /** Retorna o idioma da mídia @return idioma da mídia */
    public String getIdioma() { return this.idioma; }
    /** Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento} */
    public String toFile() { return id + ";" + nome + ";" + dataLançamento; }

    /** @formatter:on
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toString() {
        return " ID: " + id + " | Nome: " + nome + " | Data de Lançamento: " + dataLançamento + " | Audiência: "
                + audiência;
    }

}
