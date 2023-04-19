import java.io.*;
import java.util.*;

/**
 * Clase que representa uma plataforma de streaming.
 */
public class PlataformaStreaming {

    /** Nome da plataforma de streaming */
    private String nome;

    /** Nome da plataforma */
    private HashSet<Série> series;

    /** Clientes da plataforma */
    private HashSet<Cliente> clientes;

    /** Cliente atual */
    private Cliente clienteAtual;

    /**
     * Construtor da classe PlataformaStreaming.
     * 
     * @param nome Nome da plataforma de streaming.
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashSet<Série>();
        this.clientes = new HashSet<Cliente>();
        this.clienteAtual = null;
    }

    /**
     * Executa o login de um cliente.
     * 
     * @param nome_senha Nome de usuário e senha do cliente.
     * @return O cliente se o login for bem sucedido, NULL caso contrário.
     */
    public Cliente login(String[] nome_senha) {
        for (Cliente cliente : this.clientes) // itera sobre o HashSet
            // verifica se o cliente possui o nome de usuário e se a senha está correta
            if (cliente.loginUser(nome_senha[0]) && cliente.loginPassword(nome_senha[1])) {
                this.clienteAtual = cliente;
                return cliente;
            }
        return null; // se não encontrar nenhum cliente com o nome de usuário, retorna null
    }

    /**
     * Adiciona um cliente na plataforma de streaming.
     * 
     * @param série nova série a ser adicionada.
     */
    public void adicionarSérie(Série série) {
        this.series.add(série);
    }

    /**
     * Adiciona um cliente na plataforma de streaming.
     * 
     * @param cliente novo cliente a ser adicionado.
     */
    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    /**
     * Retorna uma lista com todas as séries da plataforma de streaming
     * participantes de um determinado gênero.
     * 
     * @param gênero gênero das séries a serem filtradas
     * @return Lista com as séries do gênero passado como parâmetro
     */
    public List<Série> filtarPorGênero(String gênero) {
        List<Série> seriesFiltradas = new Stack<Série>();
        for (Série série : this.series)
            if (série.possuiGênero(gênero))
                seriesFiltradas.add(série);
        return seriesFiltradas;
    }

    /**
     * Retorna uma lista com todas as séries da plataforma de streaming
     * que possuem um determinado idioma.
     * 
     * @param idioma idioma das séries a serem filtradas
     * @return Lista com as séries do idioma passado como parâmetro
     */
    public List<Série> filtarPorIdioma(String idioma) {
        List<Série> seriesFiltradas = new Stack<Série>();
        for (Série série : this.series)
            if (série.possuiIdioma(idioma))
                seriesFiltradas.add(série);
        return seriesFiltradas;
    }

    /**
     * Retorna uma lista com todas as séries da plataforma de streaming
     * que possuem uma determinada quantidade de episódios.
     * 
     * @param quantidadeEpisódios quantidade de episódios das séries a serem
     *                            filtrados
     * @return Lista com as séries com a quantidade de episódios passada como
     *         parâmetro
     */
    public List<Série> filtarPorQntEpisódios(int quantidadeEpisódios) {
        List<Série> seriesFiltradas = new Stack<Série>();
        for (Série série : this.series)
            if (série.possuiEpisódios(quantidadeEpisódios))
                seriesFiltradas.add(série);
        return seriesFiltradas;
    }

    /**
     * Adiciona uma série a lista de séries ja assistidas pelo cliente atual e
     * incrementa a audiência da série.
     * 
     * @param série     a ter a audiência incrementada.
     * @param login     login do cliente que assistiu a série.
     * @param senha     senha do cliente que assistiu a série.
     * @param completou se o cliente assistiu a série por completo.
     */
    public void registrarAudiência(Série série, String login, String senha, boolean completou) {
        if (série != null)
            try {
                if (completou)
                    this.login(new String[] { login, senha }).adicionarNaLista(série);
                else
                    this.login(new String[] { login, senha }).registrarAudiência(série);
                return;
            } catch (NullPointerException e) {
            }
        System.out.println("Login ou senha incorretos");
    }

    /**
     * Desloga o cliente atual.
     */
    public void logOff() {
        this.clienteAtual = null;
    }

    /**
     * Busca uma série na plataforma de streaming pelo nome.
     * 
     * @param nomeSérie nome da série a ser buscada.
     * @return Série com o nome passado como parâmetro, NULL caso não exista.
     */
    public Série buscarSérie(String nomeSérie) {
        for (Série série : this.series)
            if (série.temNome(nomeSérie))
                return série;
        return null;
    }

    /**
     * Método principal da aplicação.
     * 
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        PlataformaStreaming app = new PlataformaStreaming("Netflix");
        app: while (true)
            switch (Utilitários.menu()) {
                case 1:
                    app.lerArquivos();
                    break;
                case 2:
                    app.salvarArquivos();
                    break;
                case 3:
                    app.adicionarCliente(new Cliente(
                            System.console().readLine(" Nome: "),
                            System.console().readLine(" Senha: ") //
                    ));
                    break;
                case 4:
                    app.adicionarSérie(new Série(
                            "Ação",
                            System.console().readLine(" Nome: "),
                            "English",
                            10 //
                    ));
                    break;
                case 5:
                    app.registrarAudiência(
                            app.buscarSérie(System.console().readLine(" Nome da Série: ")),
                            System.console().readLine(" Login: "),
                            System.console().readLine(" Senha: "),
                            System.console().readLine(" A mídia já foi assistida? (s/n)").contains("s") //
                    );
                    break; /* @formatter:off
                case 6:
                    app.filtrarPorGênero();
                    break;
                case 7:
                    app.filtrarPorIdioma();
                    break;
                case 8:
                    app.filtrarPorQntEpisódios();
                    break; @formatter:on */
                case 9:
                    break app;
                default:
                    System.out.println(" ERRO: Opção inválida.");
            }
        if (System.console().readLine(" Deseja salvar os arquivos? (s/n)").contains("s"))
            app.salvarArquivos();
    }

}
