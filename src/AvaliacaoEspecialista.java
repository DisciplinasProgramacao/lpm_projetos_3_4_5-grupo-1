import java.util.HashMap;

/**
 * Avaliação feita por um cliente especialista, armazena comentários de texto e
 * notas
 */
public class AvaliacaoEspecialista implements IAvaliacoes {
    /**
     * Classe interna para armazenar comentários e notas
     */
    private class Comentario {
        /** Comentário de texto */
        private String comentario;
        /** Avaliação numérica */
        private int nota;

        /**
         * Construtor
         * 
         * @param nota       nota
         * @param comentario comentário
         */
        public Comentario(int nota, String comentario) {
            this.nota = nota;
            this.comentario = comentario;
        }

        /** Representação em string do comentário
         * @return nota */
        @Override
        public String toString() {
            return this.nota + " " + this.comentario;
        }
    }

    /** Avaliacoes do cliente */
    private HashMap<Integer, Comentario> avaliacoes;

    /**
     * Torando especialista
     * 
     * @param avaliacoes notas antigas
     */
    public AvaliacaoEspecialista(HashMap<Integer, Integer> avaliacoes) {
        this.avaliacoes = new HashMap<Integer, Comentario>();
        avaliacoes.keySet().stream()
                .map(
                        idMidia -> this.avaliacoes.put(idMidia, new Comentario(avaliacoes.get(idMidia), "")) //
                );
    }

    @Override
    public String get(int idMidia) {
        return avaliacoes.get(idMidia).toString();
    }

    @Override
    public void avaliar(int idMidia, int avaliacao) {
        avaliacoes.put(idMidia, new Comentario(avaliacao, App.lerStr("Digite um comentário: ")));
    }

    @Override
    public IAvaliacoes goPrev() {
        return new AvaliacaoComum(this.avaliacoes.keySet().stream()
                .collect(
                        HashMap<Integer, Integer>::new,
                        (map, idMidia) -> map.put(idMidia, avaliacoes.get(idMidia).nota),
                        HashMap<Integer, Integer>::putAll //
                ));
    }

    @Override
    public IAvaliacoes goNext() {
        throw new UnsupportedOperationException("Não há avaliação posterior");
    }

}
