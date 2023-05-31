import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Cliente implements ICliente {
    /** Nome de usuario do cliente */
    private String nomeDeUsuario,

            /** Login do cliente */
            login,

            /** Senha do cliente */
            senha;

    /** Lista de series para ver */
    private List<IMidia> listaParaVer,

            /** Lista de series vistas */
            listaJaVistas;

    /** Avaliacoes do cliente */
    private HashMap<Integer, Integer> avaliacoes;

    /** Quantidade de avaliacoes feitas pelo cliente no ultimo mes */
    private int avaliacoesAtuais;

    /**
     * Construtor da classe Cliente
     * 
     * @param nomeDeUsuario nome de usuario do cliente
     * @param login         login do cliente
     * @param senha         senha do cliente
     */
    public Cliente(String nomeDeUsuario, String login, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.login = login;
        this.senha = senha;
        this.listaParaVer = new Stack<IMidia>();
        this.listaJaVistas = new Stack<IMidia>();
        this.avaliacoes = new HashMap<Integer, Integer>();
        this.avaliacoesAtuais = 0;
    }

    /**
     * Caso o cliente passe a ter 5 avaliacoes no ultimo mes, ele se torna um
     * especialista
     * 
     * @return Cliente especialista
     */
    public ClienteEspecialista tornarEspecialista() {
        return new ClienteEspecialista(this.nomeDeUsuario, this.login, this.senha, this.listaParaVer,
                this.listaJaVistas, this.avaliacoes);
    }

    /**
     * Adiciona uma midia na lista de series para ver
     * 
     * @param midia a ser adicionada
     */
    @Override
    public void adicionarNaLista(IMidia midia) {
        this.listaParaVer.add(midia);
    }

    /**
     * Adiciona uma midia na lista de series ja vistas, como o cliente comum nao
     * pode dar avaliacao, o comentario e vazio
     * 
     * @param midia     a ser adicionada
     * @param avaliacao avaliacao da midia
     * @param data      data de visualizacao
     */
    @Override
    public ICliente registrarAudiencia(IMidia midia, int avaliacao, LocalDate data) {
        this.listaJaVistas.add(midia);
        midia.registrarAudiencia();
        if (avaliacao != 0) {
            this.avaliacoes.put(midia.getID(), avaliacao);
            midia.registrarAvaliacao(avaliacao);
        }
        if (ChronoUnit.DAYS.between(data, LocalDate.now()) <= 30 && ++this.avaliacoesAtuais == 5)
            return this.tornarEspecialista();
        return this;
    }

    /**
     * Remove uma midia da lista de series para ver
     * 
     * @param nomeMidia nome da midia a ser removida
     */
    @Override
    public void retirarDaLista(String nomeMidia) {
        this.listaParaVer.removeIf(midia -> midia.getNome().equals(nomeMidia));
    }

    /**
     * Filtra a lista de series para ver por genero
     * 
     * @param genero a ser filtrado
     * @return lista de series filtrada
     */
    @Override
    public Stream<String> filtrarPorGenero(String genero) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getGenero().equals(genero))
                .map(midia -> {
                    Integer rating = this.avaliacoes.get(midia.getID());
                    return midia + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Filtra a lista de series para ver por idioma
     * 
     * @param idioma a ser filtrado
     * @return lista de series filtrada
     */
    @Override
    public Stream<String> filtrarPorIdioma(String idioma) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getIdioma().equals(idioma))
                .map(i -> {
                    Integer rating = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Filtra a lista de series para ver por quantidade de episodios
     * 
     * @param qntsEpisodios quantidade de episodios a ser filtrada
     * @return lista de series filtrada
     */
    @Override
    public Stream<String> filtrarPorQntEpisodios(int qntsEpisodios) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getQntEp() == qntsEpisodios)
                .map(i -> {
                    Integer rating = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Filtra a lista de filmes para ver por duracao
     * 
     * @param duracao a ser filtrada
     * @return lista de filmes filtrada
     */
    @Override
    public Stream<String> filtrarPorDuracao(int duracao) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getDuracao() == duracao)
                .map(i -> {
                    Integer rating = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Verifica se o cliente inseriu a senha correta
     * 
     * @param senha a ser verificada
     * @return TRUE se a senha esta correta, FALSE caso contrario
     */
    @Override
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
        return this.nomeDeUsuario + ";" + this.login + ";" + this.senha;
    }

    /**
     * Retorna um array de Strings com as audiencias do cliente, a primeira parte do
     * array para series a ver e a segunda para series ja vistas
     *
     * Audiencia e armazenada da forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * series a assistir futuramente e “A” para series ja assistidas.
     * 
     * @return array de Strings com todas as audiencias do cliente
     */
    @Override
    public String[] audiencias() {
        int paraVerSize = this.listaParaVer.size(),
                sizeTotal = paraVerSize + this.listaJaVistas.size();
        String[] audiencias = new String[sizeTotal];

        for (int i = 0; i < paraVerSize; i++)
            audiencias[i] = this.login + ";" + "A" + ";" + this.listaParaVer.get(i).getID();

        for (int i = paraVerSize; i < sizeTotal; i++)
            audiencias[i] = this.login + ";" + "F" + ";" + this.listaJaVistas.get(i - paraVerSize).getID();

        return audiencias;
    }

    /**
     * Retorna o nome de usuario do cliente
     * 
     * @return nome de usuario do cliente
     */
    @Override
    public String getLogin() {
        return this.login;
    }

    /**
     * Retorna a avaliacao de uma midia
     * 
     * @param idMidia id da midia a ser buscada
     */
    @Override
    public int getAvaliacao(int idMidia) {
        return this.avaliacoes.get(idMidia);
    }

    /**
     * Retorna lista para ver
     * 
     * @return quantidade de midias para ver
     */
    public List<IMidia> getListaParaVer() {
        return this.listaParaVer;
    }

    /**
     * Retorna lista ja vistas
     * 
     * @return quantidade de midias ja vistas
     */
    public List<IMidia> getListaJaVistas() {
        return this.listaJaVistas;
    }

    /**
     * Retorna avaliacoes
     * 
     * @return avaliacoes
     */
    public HashMap<Integer, Integer> getAvaliacoes() {
        return this.avaliacoes;
    }
}
