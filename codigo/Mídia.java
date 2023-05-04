import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe Mídia, armazena os dados de um tipo de mídia.
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
    protected final int ID;
    /** Total de audiência da mídia */
    protected int audiência,
            /** Rating da mídia */
            ratingMédio,
            /** Quantidade de avaliações da mídia */
            qntAvaliações;

    /** Data de lançamento da mídia */
    protected LocalDate dataLançamento; // @formatter:on

    /**
     * Construtor da classe Mídia
     * 
     * @param ID             ID da mídia
     * @param gênero         gênero da mídia
     * @param nome           nome da mídia
     * @param idioma         idioma da mídia
     * @param dataLançamento data de lançamento da mídia
     */
    public Mídia(int ID, String gênero, String nome, String idioma, LocalDate dataLançamento) {
        this.ID = ID;
        this.gênero = gênero;
        this.nome = nome;
        this.idioma = idioma;
        this.dataLançamento = dataLançamento;
        this.audiência = 0;
        this.ratingMédio = 0;
        this.qntAvaliações = 0;
    }

    /**
     * Registra uma avaliação da mídia
     * 
     * @param avaliação avaliação da mídia
     */
    public void registrarAvaliação(int avaliação) {
        this.ratingMédio = (this.ratingMédio * this.qntAvaliações + avaliação) / ++this.qntAvaliações;
    }

    /**
     * Adiciona uma audiência a série
     */
    public void registrarAudiência() {
        this.audiência++;
    }

    /**
     * Retorna o nome da mídia
     * 
     * @return nome da mídia
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o gênero da mídia
     * 
     * @return gênero da mídia
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Retorna o gênero da mídia
     * 
     * @return gênero da mídia
     */
    public String getGênero() {
        return this.gênero;
    }

    /**
     * Retorna o idioma da mídia
     * 
     * @return idioma da mídia
     */
    public String getIdioma() {
        return this.idioma;
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     * 
     * @return String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    public String toFile() {
        return this.ID + ";" + this.nome + ";" + this.dataLançamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toString() {
        return " ID: " + this.ID + " | Nome: " + this.nome + " | Gênero: " + this.gênero + " | Data de Lançamento: "
                + this.dataLançamento + " | Audiência: " + this.audiência + " | Rating: " + this.ratingMédio;
    }

}
