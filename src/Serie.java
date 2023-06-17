import java.time.LocalDate;

/**
 * Classe Serie, armazena os dados de uma serie, extende a classe Mídia.
 */
public class Serie extends Midia {

    public static final String ARQUIVO = "data/Series.csv";

    /** Quantidade de episodios da serie */
    private int qntEp;

    /**
     * Construtor da classe Serie para leitura de arquivos
     * 
     * @param id             id da serie
     * @param nome           nome da serie
     * @param dataLançamento data de lançamento da serie
     */
    public Serie(int id, String nome, LocalDate dataLançamento) {
        super(id, nome, dataLançamento);
        this.qntEp = 10;
    }

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
        super(id, genero, nome, idioma, dataLançamento);
        this.qntEp = qntEp;
    }

    /**
     * Converte o objeto em uma String legível
     */
    @Override
    public String toString() {
        return super.toString() + " | Quantidade de episodios: " + this.qntEp + " |";
    }

    /**
     * Retorna a quantidade de episodios da serie
     * 
     * @return quantidade de episodios da serie
     */
    @Override
    public int getQntEp() {
        return this.qntEp;
    }
}