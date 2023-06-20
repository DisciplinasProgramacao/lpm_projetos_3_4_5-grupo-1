import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * Classe que representa um cliente da plataforma de streaming
 */
public class Cliente {
    /** Quantidade de avaliacoes necessarias para se tornar um especialista */
    private static final int AVALIACOES_PARA_ESPECIALISTA = 5;

    /** Arquivo de clientes */
    public static final String ARQUIVO = "data/Espectadores.csv";

    /** Nome de usuario do cliente */
    private String nomeDeUsuario,
            /** Login do cliente */
            login,
            /** Senha do cliente */
            senha;

    /** Lista de series para ver */
    private List<Midia> listaParaVer,
            /** Lista de series vistas */
            listaJaVistas;

    /** Avaliacoes do cliente */
    // private HashMap<Integer, IAvaliacao> avaliacoes;

    /** Avaliacoes do cliente */
    private Avaliacoes avaliacoes;

    /** Quantidade de avaliacoes feitas pelo cliente no ultimo mes */
    private int avaliacoesAtuais;

    /** Se o cliente é um especialista */
    private boolean especialista;

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
        this.listaParaVer = new Stack<Midia>();
        this.listaJaVistas = new Stack<Midia>();
        this.avaliacoes = new AvaliacaoComum();
        this.avaliacoesAtuais = 0;
        this.especialista = false;
    }

    /**
     * Caso o cliente passe a ter 5 avaliacoes no ultimo mes, ele se torna um
     * especialista
     */
    public void tornarEspecialista() {
        this.avaliacoes = this.avaliacoes.goNext();
        this.especialista = true;
    }

    /**
     * Adiciona uma midia na lista de midias para ver
     * 
     * @param midia a ser adicionada
     */
    public void adicionarNaLista(Midia midia) {
        if (midia.isLancamento() && !this.especialista) {
            System.out.println(" Midia em lancamento, voce precisa se tornar um cliente especilista para interagir.");
            return;
        }
        this.listaParaVer.add(midia);
    }

    /**
     * Adiciona uma midia na lista de midias ja vistas.
     * 
     * @param midia a ser adicionada
     */
    public void registrarAudiencia(Midia midia) {
        if (midia.isLancamento() && !this.especialista) {
            System.out.println(" Midia em lancamento, voce precisa se tornar um cliente especilista para interagir.");
            return;
        }
        if (!this.listaJaVistas.contains(midia)) {
            this.listaJaVistas.add(midia);
            midia.registrarAudiencia();
        } else
            System.out.println("Você já assistiu a essa série!: " + midia.getNome() + "cliente: " + this.nomeDeUsuario);
    }

    /**
     * Adiciona um comentario a uma midia
     * 
     * @param midia     a ser adicionada
     * @param avaliacao avaliacao da midia
     * @param data      data de visualizacao
     */
    public void registrarAvaliacao(Midia midia, int avaliacao, LocalDate data) {
        if (midia.isLancamento() && !this.especialista) {
            System.out.println(" Midia em lancamento, voce precisa se tornar um cliente especilista para interagir.");
            return;
        }

        this.listaJaVistas.add(midia);

        if (!this.listaJaVistas.contains(midia))
            midia.registrarAudiencia();

        if (ChronoUnit.DAYS.between(data, LocalDate.now()) <= 30
                && ++this.avaliacoesAtuais == AVALIACOES_PARA_ESPECIALISTA)
            this.tornarEspecialista();
    }

    /**
     * Remove uma midia da lista de midias para ver
     * 
     * @param nomeMidia nome da midia a ser removida
     */
    public void retirarDaLista(String nomeMidia) {
        this.listaParaVer.removeIf(midia -> midia.getNome().equals(nomeMidia));
    }

    /**
     * Filtra a lista de midias para ver por genero
     * 
     * @param genero a ser filtrado
     * @return lista de midias filtrada
     */
    public Stream<String> filtrarPorGenero(String genero) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getGenero().equals(genero))
                .map(midia -> {
                    String rating = this.avaliacoes.get(midia.getID());
                    return midia + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Filtra a lista de midias para ver por idioma
     * 
     * @param idioma a ser filtrado
     * @return lista de midias filtrada
     */
    public Stream<String> filtrarPorIdioma(String idioma) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getIdioma().equals(idioma))
                .map(i -> {
                    String rating = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Filtra a lista de series para ver por quantidade de episodios
     * 
     * @param qntsEpisodios quantidade de episodios a ser filtrada
     * @return lista de series filtrada
     */
    public Stream<String> filtrarPorQntEpisodios(int qntsEpisodios) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getQntEp() == qntsEpisodios)
                .map(i -> {
                    String rating = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Filtra a lista de filmes para ver por duracao
     * 
     * @param duracao a ser filtrada
     * @return lista de filmes filtrada
     */
    public Stream<String> filtrarPorDuracao(int duracao) {
        return this.listaJaVistas.stream()
                .filter(midia -> midia.getDuracao() == duracao)
                .map(i -> {
                    String rating = this.avaliacoes.get(i.getID());
                    return i + " | Seu rating: " + (rating == null ? "Voce nao deu rating" : rating);
                });
    }

    /**
     * Verifica se o cliente inseriu a senha correta
     * 
     * @param senha a ser verificada
     * @return TRUE se a senha esta correta, FALSE caso contrario
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
    public String getLogin() {
        return this.login;
    }

    /**
     * Retorna a avaliacao de uma midia
     * 
     * @param idMidia id da midia a ser buscada
     * @return avaliacao da midia em String
     */
    public String getAvaliacao(int idMidia) {
        return this.avaliacoes.get(idMidia);
    }

    /**
     * Retorna lista para ver
     * 
     * @return quantidade de midias para ver
     */
    public List<Midia> getListaParaVer() {
        return this.listaParaVer;
    }

    /**
     * Retorna lista ja vistas
     * 
     * @return quantidade de midias ja vistas
     */
    public List<Midia> getListaJaVistas() {
        return this.listaJaVistas;
    }

    /**
     * Retorna a classe Avaliacoes
     * 
     * @return avaliacoes do cliente
     */
    public Avaliacoes getAvaliacoes() {
        return this.avaliacoes;
    }

    /**
     * Retorna o total de midias assistidas pelo cliente
     * 
     * @return total de midias assistidas
     */
    public Integer getQntMidiasAssistidas() {
        return this.listaJaVistas.size();
    }

    /**
     * Retorna o nome de usuario do cliente
     * 
     * @return nome de usuario do cliente
     */
    public String getNome() {
        return this.nomeDeUsuario;
    }

}
