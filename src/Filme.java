import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe Filme, armazena os dados de um filme, extende a classe Mídia.
 */
public class Filme implements IMidia {

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
            /** Duracao do filme em minutos */
            duracao;

    /** Data de lançamento da mídia */
    private LocalDate dataLançamento;

    /**
     * Construtor da classe Filme para leitura de arquivos
     * 
     * @param id             do filme
     * @param nome           do filme
     * @param dataLançamento data de lançamento do filme
     * @param duracao        do filme
     */
    public Filme(int id, String nome, LocalDate dataLançamento, int duracao) {
        this.ID = id;
        this.genero = Genero.sortearGenero().getNome();
        this.nome = nome;
        this.idioma = Idioma.sortearIdioma().getNome();
        this.dataLançamento = dataLançamento;
        this.audiencia = 0;
        this.ratingMedio = 0;
        this.qntAvaliacoes = 0;
        this.duracao = duracao;
    }

    /**
     * Construtor da classe Filme
     * 
     * @param id             do filme
     * @param genero         do filme
     * @param nome           do filme
     * @param idioma         do filme
     * @param dataLançamento data de lançamento do filme
     * @param duracao        do filme
     */
    public Filme(int id, String genero, String nome, String idioma, LocalDate dataLançamento, int duracao) {
        this.ID = id;
        this.genero = genero;
        this.nome = nome;
        this.idioma = idioma;
        this.dataLançamento = dataLançamento;
        this.audiencia = 0;
        this.ratingMedio = 0;
        this.qntAvaliacoes = 0;
        this.duracao = duracao;
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
        return this.ID + ";" + this.nome + ";" + this.dataLançamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ";" + this.duracao;
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toString() {
        return " ID: " + this.ID + " | Nome: " + this.nome + " | Genero: " + this.genero + " | Data de Lançamento: "
                + this.dataLançamento + " | Audiencia: " + this.audiencia + "| Avaliacoes: " + this.qntAvaliacoes
                + " | Rating: " + this.ratingMedio;
    }

    // @formatter:off
    /** Retorna o nome da mídia
     * @return nome da mídia*/
    public String getNome() { return this.nome; }
    /** Retorna o id da mídia
     * @return id da mídia*/
    public int getID() { return this.ID; }
    /** Retorna o genero da mídia
     * @return genero da mídia*/
    public String getGenero() { return this.genero; }
    /** Retorna o idioma da mídia
     * @return idioma da mídia*/
    public String getIdioma() { return this.idioma; }
    /** Retorna a duracao do filme
     * @return duracao do filme*/
    public int getDuracao() { return this.duracao; }
    /** Retorna a quantidade de temporadas da mídia, retorna -1 pois a mídia não é uma série
     * @return quantidade de temporadas da mídia*/
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
}
