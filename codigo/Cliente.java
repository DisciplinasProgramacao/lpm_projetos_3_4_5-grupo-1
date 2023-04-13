import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;

    public adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    public retirarDaLista(String nomeSerie) {
    }

    public filtrarPorGenero(String genero) {
    }

    public filtrarPorIdioma(String idioma) {
    }

    public filtrarPorQntsEpisodios( int qntsEpisodios) {
    }
    
    public registrarAudiencia( int qntsTemporadas) {
    }
}
