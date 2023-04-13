/**
 * Classe Serie
 */
public class Serie {
    /**
     * Atributos da classe Serie
     */
    private final String[] GENEROS;
    private String nome;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    /**
     * Construtor da classe Serie
     * @param qntGen
     * @param nome
     * @param idioma
     * @param quantidadeEpisodios
     */
    public Serie(String[] qntGen, String nome, String idioma, int quantidadeEpisodios){
        this.GENEROS = qntGen;
        this.nome = nome;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios;
        this.audiencia = 0;
    }

    /**
     * Adiciona uma audiencia a serie
     */
    public void registrarAudiencia(){
        audiencia++;
    }

    /**
     * Retorna a audiencia da serie
     */
    public int getAudiencia(){
        return audiencia;
    }

}

