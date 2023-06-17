import java.util.HashMap;

/**
 * Avaliação inicial que o cliente pode fazer, armaena apenas a nota de uma
 * midia
 */
public class AvaliacaoComum extends Avaliacoes {

    /**
     * Primeira avaliação
     */
    public AvaliacaoComum() {
        super();
    }

    /**
     * Alterando o estado de avaliação
     * 
     * @param avaliacoes avaliacoes do cliente anterior
     */
    public AvaliacaoComum(HashMap<Integer, Comentario> avaliacoes) {
        super(avaliacoes);
    }

    @Override
    public void avaliar(int idMidia, int avaliacao) {
        super.put(idMidia, new Comentario(avaliacao, new String()));
    }

    @Override
    public Avaliacoes goPrev() {
        throw new UnsupportedOperationException("Não há avaliação anterior");
    }

}
