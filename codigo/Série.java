import java.time.LocalDate;

/**
 * Classe Série, armazena os dados de uma série, extende a classe Mídia.
 */
public class Série extends Mídia {
    /** Quantidade de episódios da série */
    private int qntEp;

    /**
     * Construtor da classe Série
     * 
     * @param id     id da série
     * @param gênero gênero da série
     * @param nome   nome da série
     * @param idioma idioma da série
     * @param dataLançamento  data de lançamento da série
     * @param qntEp  quantidade de episodios da série
     */
    public Série(int id, String gênero, String nome, String idioma, LocalDate dataLançamento, int qntEp) {
        super(id, gênero, nome, idioma, dataLançamento);
        this.qntEp = qntEp;
    }

    /**
     * Retorna a quantidade de episódios da série
     * 
     * @return quantidade de episódios da série
     */
    public int getQntEp() {
        return this.qntEp;
    }

}