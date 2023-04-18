import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Classe que representa um cliente do sistema da plataforma de streaming
 */
public class Cliente {

    /** Nome de usuário do cliente */
    private String nomeDeUsuário,

            /** Senha do cliente */
            senha;

    /** Lista de séries para ver */
    private List<Série> listaParaVer,

            /** Lista de séries vistas */
            listaJaVistas;

    /**
     * Construtor da classe Cliente
     * 
     * @param nomeDeUsuário nome de usuário do cliente
     * @param senha         senha do cliente
     */
    public Cliente(String nomeDeUsuário, String senha) {
        this.nomeDeUsuário = nomeDeUsuário;
        this.senha = senha;
        this.listaParaVer = new ArrayList<Série>();
        this.listaJaVistas = new ArrayList<Série>();
    }

    /**
     * Adiciona uma série na lista de séries para ver
     * 
     * @param série a ser adicionada
     */
    public void adicionarNaLista(Série série) {
        listaParaVer.add(série);
    }

    /**
     * Remove uma série da lista de séries para ver
     * 
     * @param nomeSérie nome da série a ser removida
     */
    public void retirarDaLista(String nomeSérie) {
        for (Série série : listaParaVer)
            if (série.temNome(nomeSérie)) {
                listaParaVer.remove(série);
                break;
            }
    }

    /**
     * Filtra a lista de séries para ver por gênero
     * 
     * @param gênero a ser filtrado
     * @return lista de séries filtrada
     */
    public List<Série> filtrarPorGênero(String gênero) {
        List<Série> listaFiltrada = new Stack<Série>();
        for (Série série : listaParaVer)
            if (série.possuiGênero(gênero))
                listaFiltrada.add(série);
        return listaFiltrada;
    }

    /**
     * Filtra a lista de séries para ver por idioma
     * 
     * @param idioma a ser filtrado
     * @return lista de séries filtrada
     */
    public List<Série> filtrarPorIdioma(String idioma) {
        List<Série> listaFiltrada = new Stack<Série>();
        for (Série série : listaParaVer)
            if (série.possuiIdioma(idioma))
                listaFiltrada.add(série);
        return listaFiltrada;
    }

    /**
     * Filtra a lista de séries para ver por quantidade de episódios
     * 
     * @param qntsEpisódios quantidade de episódios a ser filtrada
     * @return lista de séries filtrada
     */
    public List<Série> filtrarPorQntsEpisódios(int qntsEpisódios) {
        List<Série> listaFiltrada = new ArrayList<Série>();
        for (Série série : listaParaVer)
            if (série.possuiEpisódios(qntsEpisódios))
                listaFiltrada.add(série);
        return listaFiltrada;
    }

    /**
     * Verifica se o cliente possui o nome de usuário passado como parâmetro
     * 
     * @param nomeDeUsuário a ser verificado
     * @return TRUE se o cliente possui o nome de usuário, FALSE caso contrário
     */
    public boolean loginUser(String nomeDeUsuário) {
        if (this.nomeDeUsuário.equals(nomeDeUsuário))
            return true;
        return false;
    }

    /**
     * Verifica se o cliente inseriu a senha correta
     * 
     * @param senha a ser verificada
     * @return TRUE se a senha está correta, FALSE caso contrário
     */
    public boolean loginPassword(String senha) {
        return this.senha.equals(senha);
    }

    /**
     * Adiciona uma série na lista de séries já vistas
     * 
     * @param série a ser adicionada
     */
    public void registrarAudiência(Série série) {
        this.listaJaVistas.add(série);
    }

    /**
     * Converte o objeto em uma String no formato: {Nome;Login;Senha}
     * 
     * @return String com os dados do cliente
     */
    @Override
    public String toString() {
        return this.nomeDeUsuário + ";" + this.nomeDeUsuário + ";" + this.senha;
    }

    /**
     * Retorna uma matriz de Strings com as audiências do cliente, o primeiro array
     * para séries a ver e o segundo para séries ja vistas
     * 
     * @return matriz de Strings com todas as audiências do cliente
     */
    public String[][] audiências() {
        String[][] audiências = new String[2][];
        audiências[0] = new String[listaParaVer.size()];
        audiências[1] = new String[listaJaVistas.size()];
        for (int i = 0; i < listaParaVer.size(); i++)
            audiências[0][i] = this.nomeDeUsuário + "/" + this.senha + ";" + "F" + ";" + listaParaVer.get(i).getNome();
        for (int i = 0; i < listaJaVistas.size(); i++)
            audiências[1][i] = this.nomeDeUsuário + "/" + this.senha + ";" + "A" + ";" + listaJaVistas.get(i).getNome();
        return audiências;
    }

}
