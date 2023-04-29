import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe Filme, armazena os dados de um filme, extende a classe Mídia.
 */
public class Filme extends Mídia {
    /** Duração do filme em minutos */
    private int duração;

    /**
     * Construtor da classe Filme
     * 
     * @param id             do filme
     * @param gênero         do filme
     * @param nome           do filme
     * @param idioma         do filme
     * @param dataLançamento data de lançamento do filme
     * @param duração        do filme
     */
    public Filme(int id, String gênero, String nome, String idioma, LocalDate dataLançamento, int duração) {
        super(id, gênero, nome, idioma, dataLançamento);
        this.duração = duração;
    }

    /**
     * Retorna a duração do filme em minutos
     * 
     * @return duração do filme em minutos
     */
    public int getDuração() {
        return this.duração;
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     * 
     * @return String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toFile() {
        return this.ID + ";" + this.nome + ";" + this.dataLançamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" + this.duração;
    }
}
