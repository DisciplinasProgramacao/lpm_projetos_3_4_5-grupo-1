import java.util.HashMap;

/**
 * Avaliação feita por um cliente especialista, armazena comentários de texto e
 * notas
 */
public class AvaliacaoEspecialista extends Avaliacoes {

    /**
     * Alterando o estado de avaliação
     * 
     * @param avaliacoes avaliacoes do cliente anterior
     */
    public AvaliacaoEspecialista(HashMap<Integer, Comentario> avaliacoes) {
        super(avaliacoes);
    }

    /**
     * Avalia uma midia
     * 
     * @idMidia id da midia a ser avaliada
     * @avaliacao nota da midia
     */
    @Override
    public void avaliar(int idMidia, int avaliacao) {
        super.put(idMidia, new Comentario(avaliacao, App.lerStr("Digite um comentario: ")));
    }

    /**
     * Avalia uma midia, MÉTODO APENAS PARA TESTES
     * Método foi criado pois não é possível inserir input pelo terminal nos testes
     * 
     * @idMidia id da midia a ser avaliada
     * @avaliacao comentário da midia
     */
    void avaliar(int idMidia, int avaliacao, String comentario) {
        super.put(idMidia, new Comentario(avaliacao, comentario));
    }

    /**
     * Não há próximo estado de avaliador, tentar seguir adiante resulta em uma
     * throw de exceção
     */
    public Avaliacoes goNext() {
        throw new UnsupportedOperationException("Nao ha avaliacao posterior");
    }

}
