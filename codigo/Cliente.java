import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;

    /**
     * Construtor da classe Cliente
     * 
     * @param nomeDeUsuario
     * @param senha
     */
    public Cliente(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
    }

    /**
     * 
     * 
     * @return the nomeDeUsuario
     */
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie) {
        for (Serie serie : listaParaVer)
            if (serie.getNome().equals(nomeSerie)) {
                listaParaVer.remove(serie);
                break;
            }
    }

    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> listaFiltrada = new ArrayList<Serie>();
        for (Serie serie : listaParaVer)
            if (serie.getGenero().equals(genero))
                listaFiltrada.add(serie);
        return listaFiltrada;
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> listaFiltrada = new ArrayList<Serie>();
        for (Serie serie : listaParaVer)
            if (serie.getIdioma().equals(idioma))
                listaFiltrada.add(serie);
        return listaFiltrada;
    }

    public List<Serie> filtrarPorQntsEpisodios(int qntsEpisodios) {
        List<Serie> listaFiltrada = new ArrayList<Serie>();
        for (Serie serie : listaParaVer)
            if (serie.getQntsEpisodios() == qntsEpisodios)
                listaFiltrada.add(serie);
        return listaFiltrada;
    }

    public void registrarAudiencia(int qntsTemporadas) {
        for (Serie serie : listaParaVer)
            if (serie.getQntsTemporadas() == qntsTemporadas) {
                listaJaVistas.add(serie);
                listaParaVer.remove(serie);
                break;
            }
    }

}
