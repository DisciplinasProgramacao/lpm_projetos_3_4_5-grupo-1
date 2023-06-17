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

    @Override
    public void avaliar(int idMidia, int avaliacao) {
        super.put(idMidia, new Comentario(avaliacao, App.lerStr("Digite um comentário: ")));
    }

    public Avaliacoes goNext() {
        throw new UnsupportedOperationException("Não há avaliação posterior");
    }

}
