/**
 * Classe Serie, armazena os dados de uma sêrie.
 */
public class Série {

    /** Array de strings que armazena os gêneros da sêrie */
    private static final String[] GENÊROS = { "Ação", "Aventura", "Comédia", "Drama", "Ficção Científica", "Romance",
            "Suspense", "Terror" };

    /** Nome da sêrie */
    private String nome,

            /** Gênero da sêrie */
            gênero,

            /** Idioma da sêrie */
            idioma;

    /** Total de episodios da sêrie */
    private int quantidadeEpisódios,

            /** Total de audiência da sêrie */
            audiência;

    /**
     * Construtor da classe Serie
     * 
     * @param gênero gênero da sêrie
     * @param nome   nome da sêrie
     * @param idioma idioma da sêrie
     * @param qntEp  quantidade de episodios da sêrie
     */
    public Série(String gênero, String nome, String idioma, int qntEp) {
        this.gênero = gênero;
        this.nome = nome;
        this.idioma = idioma;
        this.quantidadeEpisódios = qntEp;
        this.audiência = 0;
    }

}
