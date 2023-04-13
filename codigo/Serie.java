public class Serie {
    private final String[] GENEROS;
    private String nome;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    public Serie(String[] qntGen, String nome, String idioma, int quantidadeEpisodios){
        this.GENEROS = qntGen;
        this.nome = nome;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios;
        this.audiencia = 0;
    }

    public void registrarAudiencia(){
        audiencia++;
    }

}
