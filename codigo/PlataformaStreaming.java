import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Clase que representa uma plataforma de streaming.
 */
public class PlataformaStreaming {

    /** Nome da plataforma de streaming */
    private String nome;

    /** Séries da plataforma */
    private HashMap<Integer, Série> séries;

    /** Filmes da plataforma */
    private HashMap<Integer, Filme> filmes;

    /** Clientes da plataforma */
    private HashMap<String, Cliente> clientes;

    /** Cliente atual, sem uso. */
    private Cliente clienteAtual;

    /**
     * Construtor da classe PlataformaStreaming.
     * 
     * @param nome Nome da plataforma de streaming.
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.séries = new HashMap<Integer, Série>();
        this.filmes = new HashMap<Integer, Filme>();
        this.clientes = new HashMap<String, Cliente>();
        this.logOff();
    }

    /**
     * Executa o login de um cliente.
     * 
     * @param user  nome de usuário do cliente.
     * @param senha senha do cliente.
     * @param admin se o login ocorre como administrador.
     */
    public void login(String user, String senha, boolean admin) {
        if (admin) // loga no cliente como administrador
            this.clienteAtual = this.clientes.get(user);

        else if (this.clientes.get(user).loginPassword(senha)) // loga no cliente como usuário comum
            this.clienteAtual = this.clientes.get(user);

        else // login ou senha incorretos
            System.out.println("Login ou senha incorretos");
    }

    /**
     * Desloga o cliente atual.
     */
    public void logOff() {
        this.clienteAtual = null;
    }

    /**
     * Adiciona uma série na plataforma de streaming.
     * 
     * @param série nova série a ser adicionada.
     */
    public void adicionarSérie(Série série) {
        this.séries.put(série.getID(), série);
    }

    /**
     * Adiciona um filme na plataforma de streaming.
     * 
     * @param filme nova filme a ser adicionada.
     */
    public void adicionarFilme(Filme filme) {
        this.filmes.put(filme.getID(), filme);
    }

    /**
     * Adiciona um cliente na plataforma de streaming.
     * 
     * @param cliente novo cliente a ser adicionado.
     */
    public void adicionarCliente(Cliente cliente) {
        this.clientes.put(cliente.getLogin(), cliente);
    }

    /**
     * Adiciona uma mídia a lista de mídias ja assistidas pelo cliente atual e
     * incrementa a audiência da mídia.
     * 
     * @param mídia     a ter a audiência incrementada.
     * @param completou se o cliente assistiu a mídia por completo.
     */
    public void registrarAudiência(boolean completou, Mídia mídia) {
        if (mídia == null) {
            System.out.println("Série não encontrada");
            return;
        }
        try {
            if (completou)
                this.clienteAtual.adicionarNaLista(mídia);
            else
                this.clienteAtual.registrarAudiência(mídia);
        } catch (NullPointerException e) {
            System.out.println(" Nenhum cliente logado, não é possível registrar audiência.");
        }
    }

    /**
     * Retorna uma lista com todas as mídias de um cliente participantes de um
     * determinado gênero.
     * 
     * @param gênero gênero das mídias a serem filtradas
     * @return Lista com as mídias do gênero passado como parâmetro
     */
    public List<Mídia> filtarPorGênero(String gênero) {
        if (this.clienteAtual != null) // Se o cliente estiver logado, filtra as mídias dele
            return this.clienteAtual.filtrarPorGênero(gênero);

        // Se não, filtra todas as mídias da plataforma
        List<Mídia> listaFiltrada = new Stack<Mídia>();
        for (Mídia série : this.séries.values())
            if (série.getGênero().equals(gênero))
                listaFiltrada.add(série);
        for (Mídia filme : this.filmes.values())
            if (filme.getGênero().equals(gênero))
                listaFiltrada.add(filme);

        System.out.println(" Nenhum cliente logado, filtrando todas as mídias da plataforma.");
        return listaFiltrada;

    }

    /**
     * Retorna uma lista com todas as mídias de um cliente que possuem um
     * determinado idioma.
     * 
     * @param idioma idioma das mídias a serem filtradas
     * @return Lista com as mídias do idioma passado como parâmetro
     */
    public List<Mídia> filtarPorIdioma(String idioma) {
        if (this.clienteAtual != null) // Se o cliente estiver logado, filtra as mídias dele
            return this.clienteAtual.filtrarPorIdioma(idioma);

        // Se não, filtra todas as mídias da plataforma
        List<Mídia> listaFiltrada = new Stack<Mídia>();
        for (Mídia série : this.séries.values())
            if (série.getIdioma().equals(idioma))
                listaFiltrada.add(série);
        for (Mídia filme : this.filmes.values())
            if (filme.getIdioma().equals(idioma))
                listaFiltrada.add(filme);

        System.out.println(" Nenhum cliente logado, filtrando todas as mídias da plataforma.");
        return listaFiltrada;
    }

    /**
     * Retorna uma lista com todas as séries de um cliente que possuem uma
     * determinada quantidade de episódios.
     * 
     * @param quantidadeEpisódios quantidade de episódios das séries a serem
     *                            filtrados
     * @return Lista com as séries com a quantidade de episódios passada como
     *         parâmetro
     */
    public List<Série> filtarPorQntEpisódios(int quantidadeEpisódios) {
        if (this.clienteAtual == null) // Se o cliente estiver logado, filtra as séries dele
            return this.clienteAtual.filtrarPorQntEpisódios(quantidadeEpisódios);

        // Se não, filtra todas as séries da plataforma
        List<Série> listaFiltrada = new Stack<Série>();
        for (Série série : this.séries.values())
            if (série.getQntEp() == quantidadeEpisódios)
                listaFiltrada.add(série);

        System.out.println(" Nenhum cliente logado, filtrando todas as séries da plataforma.");
        return listaFiltrada;
    }

    /**
     * Retorna uma lista com todos os filmes de um cliente que possuem uma
     * determinada duração.
     * 
     * @param duração duração dos filmes a serem filtrados
     * @return Lista com os filmes com a duração passada como parâmetro
     */
    public List<Filme> filtarPorDuração(int duração) {
        if (this.clienteAtual == null) // Se o cliente estiver logado, filtra os filmes dele
            return this.clienteAtual.filtrarPorDuração(duração);

        // Se não, filtra todos os filmes da plataforma
        List<Filme> listaFiltrada = new Stack<Filme>();
        for (Filme filme : this.filmes.values())
            if (filme.getDuração() == duração)
                listaFiltrada.add(filme);

        System.out.println(" Nenhum cliente logado, filtrando todos os filmes da plataforma.");
        return listaFiltrada;
    }

    /**
     * Busca uma mídia na plataforma de streaming pelo nome.
     * 
     * @param idMídia id da mídia a ser buscada.
     * @return Mídia com o nome passado como parâmetro, NULL caso não exista.
     */
    public Mídia buscarMídia(int idMídia) {
        Mídia mídia = this.séries.get(idMídia);
        if (mídia == null)
            mídia = this.filmes.get(idMídia);
        return mídia;
    }

    /**
     * Retorna uma string que representa a plataforma de streaming com o nome e
     * número de séries e clientes cadastrados.
     * 
     * @return String que representa a plataforma de streaming.
     */
    @Override
    public String toString() {
        return " Há " + this.séries.size() + " séries cadastradas na plataforma " + this.nome + " e "
                + this.clientes.size() + " clientes cadastrados.";
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
                case 1: // Ler arquivos
                    app = IOPlataforma.lerArquivos(app);
                    break;
                case 2: // Salvar arquivos
                    IOPlataforma.salvarArquivos(
                            app.clientes.values(),
                            app.séries.values(),
                            app.filmes.values() //
                    );
                    break;
                case 3: // Filtrar mídias por gênero
                    app.filtarPorGênero(System.console().readLine(" Gênero: "))
                            .forEach(System.out::println);
                    break;
                case 4: // Filtrar mídias por idioma
                    app.filtarPorIdioma(System.console().readLine(" Idioma: "))
                            .forEach(System.out::println);
                    break;
                case 5: // Filtrar séries por quantidade de episódios
                    app.filtarPorQntEpisódios(Utilitários.lerInt(" Quantidade de episódios: "))
                            .forEach(System.out::println);
                    break;
                case 6: // Filtrar filmes por duração
                    app.filtarPorDuração(Utilitários.lerInt(" Duração: "))
                            .forEach(System.out::println);
                    break;
                case 7: // Login
                    app.login(
                            System.console().readLine(" Login: "),
                            System.console().readLine(" Senha: "),
                            false // Não é administrador
                    );
                    break;
                case 8: // Logoff
                    app.logOff();
                    break;
                case 9: // Menu de adição
                    switch (Utilitários.menuAdd()) {
                        case 1: // Adicionar série
                            app.adicionarSérie(new Série(
                                    Utilitários.lerInt(" ID: "), // ID
                                    Mídia.GENÊROS[(int) Math.round(Math.random() * Mídia.GENÊROS.length)], // Gênero
                                    System.console().readLine(" Nome: "), // Nome
                                    "Português", // Idioma
                                    LocalDate.parse(
                                            System.console().readLine(" Data de lançamento (dd/MM/yyyy): "),
                                            DateTimeFormatter.ofPattern("dd/MM/yyyy") // Data de lançamento
                                    ),
                                    10 // Quantidade de episódios
                            ));
                            break;
                        case 2: // Adicionar filme
                            app.adicionarFilme(new Filme(
                                    Utilitários.lerInt(" ID: "), // ID
                                    Mídia.GENÊROS[(int) Math.round(Math.random() * Mídia.GENÊROS.length)], // Gênero
                                    System.console().readLine(" Nome: "), // Nome
                                    "Português", // Idioma
                                    LocalDate.parse(
                                            System.console().readLine(" Data de lançamento (dd/MM/yyyy): "),
                                            DateTimeFormatter.ofPattern("dd/MM/yyyy") // Data de lançamento
                                    ),
                                    Utilitários.lerInt(" Duração (min): ") // Duração
                            ));
                            break;
                        case 3: // Adicionar cliente
                            app.adicionarCliente(new Cliente(
                                    System.console().readLine(" Nome: "), // Nome
                                    System.console().readLine(" Login: "), // Login
                                    System.console().readLine(" Senha: ") // Senha
                            ));
                            break;
                        case 4: // Adicionar audiência
                            app.registrarAudiência(
                                    System.console().readLine(" A série já foi assistida? (s/n) ").contains("s"),
                                    app.buscarMídia(
                                            Utilitários.lerInt(" ID da série: ") //
                                    ));
                            break;
                        default: // Opção inválida
                            System.out.println(" Opção inválida, tente novamente.");
                        case 5: // Voltar
                    }
                    break;
                case 10: // Buscar série
                    System.out.println(app.buscarMídia(
                            Utilitários.lerInt(" ID da série: ") //
                    ));
                    break;
                case 11: 
                    break app;
                default:
                    System.out.println(" Opção inválida, tente novamente.");
            }
        if (System.console().readLine(" Deseja salvar os arquivos? (s/n)").contains("s"))
            IOPlataforma.salvarArquivos(
                    app.clientes.values(),
                    app.séries.values(),
                    app.filmes.values() //
            );
    }

}
