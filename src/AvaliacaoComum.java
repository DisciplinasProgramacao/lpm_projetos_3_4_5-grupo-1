import java.util.HashMap;

/**
 * Avaliação inicial que o cliente pode fazer, armaena apenas a nota de uma
 * midia
 */
public class AvaliacaoComum implements IAvaliacoes {
    /** Avaliacoes do cliente */
    private HashMap<Integer, Integer> avaliacoes;

    /**
     * Primeira avaliação
     */
    public AvaliacaoComum() {
        this.avaliacoes = new HashMap<Integer, Integer>();
    }

    /**
     * Voltando de especialista
     * 
     * @param avaliacoes notas antigas
     */
    public AvaliacaoComum(HashMap<Integer, Integer> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String get(int idMidia) {
        return avaliacoes.get(idMidia).toString();
    }

    @Override
    public void avaliar(int idMidia, int avaliacao) {
        avaliacoes.put(idMidia, avaliacao);
    }

    @Override
    public int size() {
        return avaliacoes.size();
    }

    @Override
    public IAvaliacoes goNext() {
        return new AvaliacaoEspecialista(this.avaliacoes);
    }

    @Override
    public IAvaliacoes goPrev() {
        throw new UnsupportedOperationException();
    }
}
