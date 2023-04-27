import java.util.List;
import java.util.Stack;

/**
 * Classe que representa um cliente do sistema da plataforma de streaming
 */
public class Cliente {

    /** Nome de usuário do cliente */
    private String nomeDeUsuário,

            /** Login do cliente */
            login,

            /** Senha do cliente */
            senha;

    /** Lista de séries para ver */
    private List<Série> listaParaVer,

            /** Lista de séries vistas */
            listaJáVistas;

    /**
     * Construtor da classe Cliente
     * 
     * @param nomeDeUsuário nome de usuário do cliente
     * @param senha         senha do cliente
     */
    public Cliente(String nomeDeUsuário, String login, String senha) {
        this.nomeDeUsuário = nomeDeUsuário;
        this.login = login;
        this.senha = senha;
        this.listaParaVer = new Stack<Série>();
        this.listaJáVistas = new Stack<Série>();
    }

    /**
     * Adiciona uma série na lista de séries para ver
     * 
     * @param série a ser adicionada
     */
    public void adicionarNaLista(Série série) {
        this.listaParaVer.add(série);
    }

    /**
     * Adiciona uma série na lista de séries já vistas
     * 
     * @param série a ser adicionada
     */
    public void registrarAudiência(Série série) {
        this.listaJáVistas.add(série);
        série.registrarAudiência();
    }

    /**
     * Remove uma série da lista de séries para ver
     * 
     * @param nomeSérie nome da série a ser removida
     */
    public void retirarDaLista(String nomeSérie) {
        for (Série série : listaParaVer)
            if (série.getNome().equals(nomeSérie)) {
                this.listaParaVer.remove(série);
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
        for (Série série : this.listaParaVer)
            if (série.getGênero().equals(gênero))
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
        for (Série série : this.listaParaVer)
            if (série.getIdioma().equals(idioma))
                listaFiltrada.add(série);
        return listaFiltrada;
    }

    /**
     * Filtra a lista de séries para ver por quantidade de episódios
     * 
     * @param qntsEpisódios quantidade de episódios a ser filtrada
     * @return lista de séries filtrada
     */
    public List<Série> filtrarPorQntEpisódios(int qntsEpisódios) {
        List<Série> listaFiltrada = new Stack<Série>();
        for (Série série : this.listaParaVer)
            if (série.getQntEp() == qntsEpisódios)
                listaFiltrada.add(série);
        return listaFiltrada;
    }

    /**
     * Verifica se o cliente possui o nome de usuário passado como parâmetro
     * 
     * @param login a ser verificado
     * @return TRUE se o cliente possui o nome de usuário, FALSE caso contrário
     */
    public boolean loginUser(String login) {
        return this.login.equals(login);
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
     * Converte o objeto em uma String no formato: {Nome;Login;Senha}
     * 
     * @return String com os dados do cliente
     */
    @Override
    public String toString() {
        return this.nomeDeUsuário + ";" + this.login + ";" + this.senha;
    }

    /**
     * Retorna uma matriz de Strings com as audiências do cliente, o primeiro array
     * para séries a ver e o segundo para séries ja vistas
     * Audiência é armazenada da forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * séries a assistir futuramente e “A” para séries já assistidas.
     * 
     * @return matriz de Strings com todas as audiências do cliente
     */
    public String[] audiências() {
        int paraVerSize = this.listaParaVer.size(),
                sizeTotal = paraVerSize + this.listaJáVistas.size();
        String[] audiências = new String[sizeTotal];

        for (int i = 0; i < paraVerSize; i++)
            audiências[i] = this.login + "/" + this.senha + ";" + "F" + ";" + this.listaParaVer.get(i).getID();

        for (int i = paraVerSize; i < sizeTotal; i++)
            audiências[i] = this.login + "/" + this.senha + ";" + "A" + ";"
                    + this.listaJáVistas.get(i - paraVerSize).getID();

        return audiências;
    }

    public String getLogin() {
        return this.login;
    }

}
