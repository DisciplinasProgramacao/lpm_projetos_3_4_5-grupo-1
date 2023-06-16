import java.time.LocalDate;

/**
 * Classe Filme, armazena os dados de um filme, extende a classe Mídia.
 */
public class Filme extends Midia {

    /** Duracao do filme em minutos */
    private int duracao;

    /**
     * Construtor da classe Filme para leitura de arquivos
     * 
     * @param id             do filme
     * @param nome           do filme
     * @param dataLançamento data de lançamento do filme
     * @param duracao        do filme
     */
    public Filme(int id, String nome, LocalDate dataLançamento, int duracao) {
        super(id, nome, dataLançamento);
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
        super(id, genero, nome, idioma, dataLançamento);
        this.duracao = duracao;
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     * 
     * @return String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toFile() {
        return super.toFile() + ";" + this.duracao;
    }

    /**
     * Converte o objeto em uma String legível
     */
    @Override
    public String toString() {
        return super.toString() + "| Duração: " + this.duracao + " minutos";
    }

    /**
     * Retorna a duracao do filme
     * 
     * @return duracao do filme
     */
    @Override
    public int getDuracao() {
        return this.duracao;
    }
}
