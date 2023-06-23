import java.util.HashMap;

/**
 * Classe abstrata que define o comportamento de avaliacao de um cliente, state
 */
public abstract class Avaliacoes {
    /**
     * Classe interna para armazenar comentários e notas
     */
    class Comentario {
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

        /**
         * Representação em string do comentário
         * 
         * @return nota
         */
        @Override
        public String toString() {
            return this.nota + " " + this.comentario;
        }
    }

    /** Avaliacoes do cliente */
    private HashMap<Integer, Comentario> avaliacoes;

    /**
     * Construtor acionado apenas na Primeira avaliação
     */
    public Avaliacoes() {
        this.avaliacoes = new HashMap<Integer, Comentario>();
    }

    /**
     * Alterando o estado de avaliação
     * 
     * @param avaliacoes avaliacoes do cliente anterior
     */
    public Avaliacoes(HashMap<Integer, Comentario> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    /**
     * Adiciona uma avaliacao no HashMap, só pode ser usado por {@link AvaliacaoComum#avaliar}
     * e {@link AvaliacaoEspecialista#avaliar}.
     * <br>~~~~ NÂO DEVE SER USADO POR OUTRAS CLASSES ~~~~
     * 
     * @param idMidia    id da midia a ser avaliada
     * @param comentario avaliacao da midia
     */
    protected void put(int idMidia, Comentario comentario) {
        avaliacoes.put(idMidia, comentario);
    }

    /**
     * Retorna a avaliacao de uma midia
     * 
     * @param idMidia id da midia a ser buscada
     * @return avaliacao da midia
     */
    public String get(int idMidia) {
        Comentario comentario = avaliacoes.get(idMidia);
        return comentario == null ? "Voce ainda nao avaliou esta midia" : comentario.toString();
    }

    /**
     * Verifica se uma midia ja foi avaliada
     * 
     * @param idMidia id da midia a ser buscada
     * @return true se a midia ja foi avaliada, false caso contrario
     */
    public boolean midiaAvaliada(int idMidia) {
        return avaliacoes.containsKey(idMidia);
    }

    /**
     * Gera uma nova avaliacao para uma midia
     * 
     * @param idMidia   id da midia a ser avaliada
     * @param avaliacao nota da midia
     */
    abstract public void avaliar(int idMidia, int avaliacao);

    /**
     * Retorna o total de midias avaliadas
     * 
     * @return total de midias avaliadas
     */
    public int total() {
        return avaliacoes.size();
    }

    /**
     * Retorna um novo contexto/estado de avaliacao
     * 
     * @return novo objeto
     */
    public Avaliacoes goNext() {
        return new AvaliacaoEspecialista(this.avaliacoes);
    }

    /**
     * Retorna um novo contexto/estado de avaliacao
     * 
     * @return novo objeto
     */
    public Avaliacoes goPrev() {
        return new AvaliacaoComum(this.avaliacoes);
    }

}
