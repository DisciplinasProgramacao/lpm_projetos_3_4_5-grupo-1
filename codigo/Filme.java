import java.time.LocalDate;

/**
 * Classe Filme, armazena os dados de um filme, extende a classe Mídia.
 */
public class Filme extends Mídia {
    /** Duração do filme em minutos */
    private int duração;

    /**
     * Construtor da classe Filme
     * 
     * @param id             id do filme
     * @param gênero         gênero do filme
     * @param nome           nome do filme
     * @param idioma         idioma do filme
     * @param duração        duração do filme
     * @param dataLançamento data de lançamento do filme
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
}
