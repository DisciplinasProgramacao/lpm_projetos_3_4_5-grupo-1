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
    private List<Mídia> listaParaVer,

            /** Lista de séries vistas */
            listaJáVistas;

    /**
     * Construtor da classe Cliente
     * 
     * @param nomeDeUsuário nome de usuário do cliente
     * @param login         login do cliente
     * @param senha         senha do cliente
     */
    public Cliente(String nomeDeUsuário, String login, String senha) {
        this.nomeDeUsuário = nomeDeUsuário;
        this.login = login;
        this.senha = senha;
        this.listaParaVer = new Stack<Mídia>();
        this.listaJáVistas = new Stack<Mídia>();
    }

    /**
     * Adiciona uma mídia na lista de séries para ver
     * 
     * @param mídia a ser adicionada
     */
    public void adicionarNaLista(Mídia mídia) {
        this.listaParaVer.add(mídia);
    }

    /**
     * Adiciona uma mídia na lista de séries já vistas
     * 
     * @param mídia a ser adicionada
     */
    public void registrarAudiência(Mídia mídia) {
        this.listaJáVistas.add(mídia);
        mídia.registrarAudiência();
    }

    /**
     * Remove uma mídia da lista de séries para ver
     * 
     * @param nomeMídia nome da mídia a ser removida
     */
    public void retirarDaLista(String nomeMídia) {
        for (Mídia mídia : listaParaVer)
            if (mídia.getNome().equals(nomeMídia)) {
                this.listaParaVer.remove(mídia);
                break;
            }
    }

    /**
     * Filtra a lista de séries para ver por gênero
     * 
     * @param gênero a ser filtrado
     * @return lista de séries filtrada
     */
    public List<Mídia> filtrarPorGênero(String gênero) {
        List<Mídia> listaFiltrada = new Stack<Mídia>();
        for (Mídia mídia : this.listaParaVer)
            if (mídia.getGênero().equals(gênero))
                listaFiltrada.add(mídia);
        return listaFiltrada;
    }

    /**
     * Filtra a lista de séries para ver por idioma
     * 
     * @param idioma a ser filtrado
     * @return lista de séries filtrada
     */
    public List<Mídia> filtrarPorIdioma(String idioma) {
        List<Mídia> listaFiltrada = new Stack<Mídia>();
        for (Mídia mídia : this.listaParaVer)
            if (mídia.getIdioma().equals(idioma))
                listaFiltrada.add(mídia);
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
        for (Mídia mídia : this.listaParaVer) {
            if (mídia instanceof Série) {
                Série série = (Série) mídia;
                if (série.getQntEp() == qntsEpisódios)
                    listaFiltrada.add(série);
            }
        }
        return listaFiltrada;
    }

    /**
     * Filtra a lista de filmes para ver por duração
     * 
     * @param duração a ser filtrada
     * @return lista de filmes filtrada
     */
    public List<Filme> filtrarPorDuração(int duração) {
        List<Filme> listaFiltrada = new Stack<Filme>();
        for (Mídia mídia : this.listaParaVer)
            if (mídia instanceof Filme) {
                Filme filme = (Filme) mídia;
                if (filme.getDuração() == duração)
                    listaFiltrada.add(filme);
            }
        return listaFiltrada;
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
     * Retorna um array de Strings com as audiências do cliente, a primeira parte do
     * array para séries a ver e a segunda para séries ja vistas
     *
     * Audiência é armazenada da forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * séries a assistir futuramente e “A” para séries já assistidas.
     * 
     * @return array de Strings com todas as audiências do cliente
     */
    public String[] audiências() {
        int paraVerSize = this.listaParaVer.size(),
                sizeTotal = paraVerSize + this.listaJáVistas.size();
        String[] audiências = new String[sizeTotal];

        for (int i = 0; i < paraVerSize; i++)
            audiências[i] = this.login + ";" + "A" + ";" + this.listaParaVer.get(i).getID();

        for (int i = paraVerSize; i < sizeTotal; i++)
            audiências[i] = this.login + ";" + "F" + ";"
                    + this.listaJáVistas.get(i - paraVerSize).getID();

        return audiências;
    }

    /**
     * Retorna o nome de usuário do cliente
     * 
     * @return nome de usuário do cliente
     */
    public String getLogin() {
        return this.login;
    }

}
