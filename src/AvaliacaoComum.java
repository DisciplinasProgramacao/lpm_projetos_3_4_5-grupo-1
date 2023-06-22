import java.util.HashMap;

/**
 * Avaliação inicial que o cliente pode fazer, armazena apenas a nota de uma
 * midia
 */
public class AvaliacaoComum extends Avaliacoes {

    /**
     * Construtor acionado apenas na Primeira avaliação
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

    /**
     * avalia uma midia
     * 
     * @idMidia id da midia a ser avaliada
     * @avaliacao nota da midia
     */
    @Override
    public void avaliar(int idMidia, int avaliacao) {
        super.put(idMidia, new Comentario(avaliacao, new String()));
    }

    /**
     * Não há estado anterior de avaliador, tentar retornar resulta em uma
     * throw de exceção
     */
    @Override
    public Avaliacoes goPrev() {
        throw new UnsupportedOperationException("Nao ha avaliacao anterior");
    }

}
