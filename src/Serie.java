import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe Serie, armazena os dados de uma serie, extende a classe Mídia.
 */
public class Serie implements IMidia {

    /** Nome da mídia */
    private String nome,
            /** Genero da mídia */
            genero,
            /** Idioma da mídia */
            idioma;

    /** Id da mídia */
    private final int ID;

    /** Total de audiencia da mídia */
    private int audiencia,
            /** Rating da mídia */
            ratingMedio,
            /** Quantidade de avaliacoes da mídia */
            qntAvaliacoes,
            /** Quantidade de episodios da serie */
            qntEp;

    /** Data de lançamento da mídia */
    private LocalDate dataLançamento;

    /**
     * Construtor da classe Serie
     * 
     * @param id             id da serie
     * @param genero         genero da serie
     * @param nome           nome da serie
     * @param idioma         idioma da serie
     * @param dataLançamento data de lançamento da serie
     * @param qntEp          quantidade de episodios da serie
     */
    public Serie(int id, String genero, String nome, String idioma, LocalDate dataLançamento, int qntEp) {
        this.ID = id;
        this.genero = genero;
        this.nome = nome;
        this.idioma = idioma;
        this.dataLançamento = dataLançamento;
        this.audiencia = 0;
        this.ratingMedio = 0;
        this.qntAvaliacoes = 0;
        this.qntEp = qntEp;
    }

    /**
     * Registra uma avaliacao da mídia
     * 
     * @param avaliacao avaliacao da mídia
     */
    @Override
    public void registrarAvaliacao(int avaliacao) {
        this.ratingMedio = (this.ratingMedio * this.qntAvaliacoes + avaliacao) / ++this.qntAvaliacoes;
    }

    /**
     * Adiciona uma audiencia a serie
     */
    @Override
    public void registrarAudiencia() {
        this.audiencia++;
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     * 
     * @return String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toFile() {
        return this.ID + ";" + this.nome + ";" + this.dataLançamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toString() {
        return " ID: " + this.ID + " | Nome: " + this.nome + " | Genero: " + this.genero + " | Data de Lançamento: "
                + this.dataLançamento + " | Audiencia: " + this.audiencia + "| Quantidade de Episodios: " + this.qntEp
                + " | Rating: " + this.ratingMedio;
    }

    // @formatter:off
    public String getNome() { return this.nome; }
    public int getID() { return this.ID; }
    public String getGenero() { return this.genero; }
    public String getIdioma() { return this.idioma; }
    public int getQntEp() { return this.qntEp; }
    public int getDuracao() { return -1; }
}