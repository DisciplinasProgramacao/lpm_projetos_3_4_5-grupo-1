import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Interface Mídia, permite uniformizar o acesso aos dados de um tipo de mídia.
 */
public abstract class Midia {

    /** Arquivo de avaliacao */
    public static final String ARQUIVO = "data/Audiencia.csv";

    /** Random */
    public static final Random RANDOM = new Random();

    /** Nome da mídia */
    private String nome,
            /** Genero da mídia */
            genero,
            /** Idioma da mídia */
            idioma;

    /** Id da mídia */
    private int id,
            /** Total de audiencia da mídia */
            audiencia,
            /** Rating da mídia */
            ratingMedio,
            /** Quantidade de avaliacoes da mídia */
            qntAvaliacoes;

    /** Data de lançamento da mídia */
    private LocalDate dataLancamento;

    /** Define se a mídia é um lançamento */
    private boolean lancamento;

    /**
     * Construtor da classe Midia para leitura de arquivos
     * 
     * @param id             da midia
     * @param nome           da midia
     * @param dataLancamento data de lançamento da midia
     */
    public Midia(int id, String nome, LocalDate dataLancamento) {
        this.id = id;
        this.genero = Genero.sortearGenero().getNome();
        this.nome = nome;
        this.idioma = Idioma.sortearIdioma().getNome();
        this.dataLancamento = dataLancamento;
        this.audiencia = 0;
        this.ratingMedio = 0;
        this.qntAvaliacoes = 0;
        this.lancamento = false;
    }

    /**
     * Construtor de uma mídia
     * 
     * @param id             da midia
     * @param genero         da midia
     * @param nome           da midia
     * @param idioma         da midia
     * @param dataLancamento data de lançamento da midia
     */
    public Midia(int id, String genero, String nome, String idioma, LocalDate dataLancamento, boolean lancamento) {
        this.id = id;
        this.genero = genero.toUpperCase();
        this.nome = nome;
        this.idioma = idioma.toUpperCase();
        this.dataLancamento = dataLancamento;
        this.audiencia = 0;
        this.ratingMedio = 0;
        this.qntAvaliacoes = 0;
        this.lancamento = lancamento;
    }

    /**
     * Registra uma avaliacao da mídia
     * 
     * @param avaliacao avaliacao da mídia
     */
    public void registrarAvaliacao(int avaliacao) {
        this.ratingMedio = (this.ratingMedio * this.qntAvaliacoes + avaliacao) / ++this.qntAvaliacoes;
    }

    /**
     * Adiciona uma audiencia a serie
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     * 
     * @return String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    public String toFile() {
        return this.id + ";" + this.nome + ";" + this.dataLancamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Converte o objeto em uma String legível
     */
    @Override
    public String toString() {
        return " id: " + this.id + " | Nome: " + this.nome + " | Genero: " + this.genero + " | Data de Lancamento: "
                + this.dataLancamento + " | Audiencia: " + this.audiencia + "| Avaliacoes: " + this.qntAvaliacoes
                + " | Rating: " + this.ratingMedio;
    }

    // @formatter:off
    /** Retorna o nome da mídia
     * @return nome da mídia*/
    public String getNome() { return this.nome; }
    /** Retorna o id da mídia
     * @return id da mídia*/
    public int getID() { return this.id; }
    /** Retorna o genero da mídia
     * @return genero da mídia*/
    public String getGenero() { return this.genero; }
    /** Retorna o idioma da mídia
     * @return idioma da mídia*/
    public String getIdioma() { return this.idioma; }
    /** Retorna a duracao do filme, sobrescrito na classe Filme
     * @return duracao do filme*/
    public int getDuracao() { return -1; }
    /** Retorna a quantidade de temporadas da serie, sobrescrito na classe Serie
     * @return quantidade de temporadas da serie*/
    public int getQntEp() { return -1; }
    /** Retorna a quantidade de avaliacoes da mídia 
     * @return quantidade de avaliacoes da mídia*/
    public int getQntAvaliacoes() { return this.qntAvaliacoes; }
    /** Retorna o rating medio da mídia 
     * @return rating medio da mídia*/
    public int getRatingMedio() { return this.ratingMedio; }
    /** Retorna a data de lançamento da mídia 
     * @return data de lançamento da mídia*/
    public int getAudiencia() { return this.audiencia; }
    /** Retorna se a mídia é lançamento 
     * @return se a mídia é lançamento*/
    public boolean isLancamento() { return this.lancamento; }

}
