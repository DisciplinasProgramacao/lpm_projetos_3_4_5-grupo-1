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

    /**
     * Adiciona uma audiência a série
     */
    public void registrarAudiência() {
        audiência++;
    }

    /**
     * Verifica se a sêrie possui o nome passado como parâmetro
     * 
     * @param nome a ser verificado
     * @return TRUE se a série possui o nome, FALSE caso contrário
     */
    public boolean temNome(String nome) {
        return this.nome.equals(nome);
    }

    /**
     * Verifica se a sêrie possui o gênero passado como parâmetro
     * 
     * @param gênero a ser verificado
     * @return TRUE se a série possui o gênero, FALSE caso contrário
     */
    public boolean possuiGênero(String gênero) {
        return this.gênero.equals(gênero);
    }

    /**
     * Verifica se a sêrie possui o idioma passado como parâmetro
     * 
     * @param idioma a ser verificado
     * @return TRUE se a sêrie possui o idioma, FALSE caso contrario
     */
    public boolean possuiIdioma(String idioma) {
        return this.idioma.equals(idioma);
    }

    /**
     * Verifica se a sêrie possui a quantidade de episodios passada como parâmetro
     * 
     * @param qntEp a ser verificado
     * @return TRUE se a sêrie possui a quantidade de episodios, FALSE caso
     *         contrario
     */
    public boolean possuiEpisódios(int qntEp) {
        return this.quantidadeEpisódios == qntEp;
    }

    /**
     * Converte o objeto em uma String no formato: {IdSerie;Nome;DataDeLançamento}
     */
    @Override
    public String toString() {
        return nome + ";" + nome + ";" + "10";
    }

    /**
     * Retorna o nome da série, usado em {@link Cliente#audiências()}
     * 
     * @return nome da série
     */
    public String getNome() {
        return nome;
    }

}
