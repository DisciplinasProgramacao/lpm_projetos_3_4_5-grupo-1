import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Clientes especialistas sao clientes que ja assistiram pelo menos 5 series nos
 * ultimos 30 dias e que podem avaliar midias com comentarios alem de notas
 */
public class ClienteEspecialista implements ICliente {

    /** Classe que representa uma avaliacao */
    private class Avaliacao {
        /** Avaliacao */
        int avaliacao;
        /** Comentario */
        String comentario;

        /**
         * Construtor da classe Avaliacao
         * 
         * @param avaliacao  avaliacao
         * @param comentario comentario
         */
        public Avaliacao(int avaliacao, String comentario) {
            this.avaliacao = avaliacao;
            this.comentario = comentario;
        }

        /**
         * Conversao para string
         * 
         * @return avaliacao
         */
        @Override
        public String toString() {
            return this.avaliacao + " - " + this.comentario;
        }
    }

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
    private HashMap<Integer, Avaliacao> avaliacoes;

    /** Quantidade de avaliacoes feitas pelo cliente no ultimo mes */
    private int avaliacoesAtuais;

    /**
     * Conversor de Cliente para ClienteEspecialista
     * 
     * @param nomeDeUsuario nome de usuario do cliente
     * @param login         login do cliente
     * @param senha         senha do cliente
     * @param listaParaVer  lista de series para ver
     * @param listaJaVistas lista de series vistas
     * @param avaliacoes    avaliacoes do cliente
     */
    public ClienteEspecialista(String nomeDeUsuario, String login, String senha, List<IMidia> listaParaVer,
            List<IMidia> listaJaVistas, HashMap<Integer, Integer> avaliacoes) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.login = login;
        this.senha = senha;
        this.listaParaVer = listaParaVer;
        this.listaJaVistas = listaJaVistas;
        this.avaliacoes = new HashMap<>();
        this.avaliacoesAtuais = 5;

        // Converte o HashMap de avaliacoes em int para um HashMap de Avaliacao com
        // avaliacao e sem comentarios
        avaliacoes.keySet()
                .forEach(key -> this.avaliacoes.put(key, new Avaliacao(avaliacoes.get(key), null)));
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
     * Adiciona uma midia na lista de series ja vistas
     * 
     * @param midia     a ser adicionada
     * @param avaliacao avaliacao da midia
     * @param data      data em que a midia foi assistida
     */
    @Override
    public ICliente registrarAudiencia(IMidia midia, int avaliacao, LocalDate data) {
        this.listaJaVistas.add(midia);
        midia.registrarAudiencia();
        if (avaliacao != 0) {
            this.avaliacoes.put(midia.getID(),
                    new Avaliacao(avaliacao, System.console().readLine(" Digite um comentario: ")) //
            );
            midia.registrarAvaliacao(avaliacao);
        }
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
                .map(i -> {
                    Avaliacao avaliacao = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (avaliacao == null ? "Voce nao deu rating" : avaliacao);
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
                    Avaliacao avaliacao = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (avaliacao == null ? "Voce nao deu rating" : avaliacao);
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
                    Avaliacao avaliacao = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (avaliacao == null ? "Voce nao deu rating" : avaliacao);
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
                    Avaliacao avaliacao = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (avaliacao == null ? "Voce nao deu rating" : avaliacao);
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
        int paraVerSize = this.listaParaVer.size(), sizeTotal = paraVerSize + this.listaJaVistas.size();
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
        Avaliacao avaliacao = this.avaliacoes.get(idMidia);
        return avaliacao == null ? 0 : avaliacao.avaliacao;
    }

}
