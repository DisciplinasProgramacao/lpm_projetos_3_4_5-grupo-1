import java.util.ArrayList;
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
     * Adiciona uma série na lista de séries para ver
     */
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    /**
     * Remove uma série da lista de séries para ver
     */
    public void retirarDaLista(String nomeSerie) {
        for (Serie serie : listaParaVer)
            if (serie.getNome().equals(nomeSerie)) {
                listaParaVer.remove(serie);
                break;
            }
    }

    /**
     * Filtra a lista de séries para ver por gênero
     * 
     * @return lista de séries filtrada
     */
    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> listaFiltrada = new ArrayList<Serie>();
        for (Serie serie : listaParaVer)
            if (serie.getGenero().equals(genero))
                listaFiltrada.add(serie);
        return listaFiltrada;
    }

    /**
     * Filtra a lista de séries para ver por idioma
     * 
     * @return lista de séries filtrada
     */
    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> listaFiltrada = new ArrayList<Serie>();
        for (Serie serie : listaParaVer)
            if (serie.getIdioma().equals(idioma))
                listaFiltrada.add(serie);
        return listaFiltrada;
    }

    /**
     * Filtra a lista de séries para ver por quantidade de temporadas
     * 
     * @return lista de séries filtrada
     */
    public List<Serie> filtrarPorQntsEpisodios(int qntsEpisodios) {
        List<Serie> listaFiltrada = new ArrayList<Serie>();
        for (Serie serie : listaParaVer)
            if (serie.getQntsEpisodios() == qntsEpisodios)
                listaFiltrada.add(serie);
        return listaFiltrada;
    }

    /**
     * Filtra a lista de séries para ver por quantidade de temporadas
     */
    public void registrarAudiencia(int qntsTemporadas) {
        for (Serie serie : listaParaVer)
            if (serie.getQntsTemporadas() == qntsTemporadas) {
                listaJaVistas.add(serie);
                listaParaVer.remove(serie);
                break;
            }
    }

}
