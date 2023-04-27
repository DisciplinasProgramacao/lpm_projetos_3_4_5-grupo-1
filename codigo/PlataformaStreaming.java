import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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
     * @param nome Nome de usuário do cliente.
     * @return O cliente se o login for bem sucedido, NULL caso contrário.
     */
    public Cliente login(String user) {
        return (this.clienteAtual = this.clientes.get(user)); // se não encontrar cliente com o nome de usuário, retorna null
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
     * @return O cliente adicionado.
     */
    public void adicionarCliente(Cliente cliente) {
        this.clientes.put(cliente.getLogin(), cliente);
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
    public void registrarAudiência(boolean completou, Série série) {
        if (série == null) {
            System.out.println("Série não encontrada");
            return;
        }
        try {
            if (completou)
                this.clienteAtual.adicionarNaLista(série);
            else
                this.clienteAtual.registrarAudiência(série);
        } catch (NullPointerException e) {
            System.out.println("Login ou senha incorretos");
        }
    }

    /**
     * Retorna uma lista com todas as séries de um cliente participantes de um
     * determinado gênero.
     * 
     * @param gênero gênero das séries a serem filtradas
     * @return Lista com as séries do gênero passado como parâmetro
     */
    public List<Série> filtarPorGênero(String gênero) {
        if (this.clienteAtual == null) {
            System.out.println("Nenhum cliente logado");
            return new Stack<Série>();
        }
        return this.clienteAtual.filtrarPorGênero(gênero);

    }

    /**
     * Retorna uma lista com todas as séries de um cliente que possuem um
     * determinado idioma.
     * 
     * @param idioma idioma das séries a serem filtradas
     * @return Lista com as séries do idioma passado como parâmetro
     */
    public List<Série> filtarPorIdioma(String idioma) {
        if (this.clienteAtual == null) {
            System.out.println("Nenhum cliente logado");
            return new Stack<Série>();
        }
        return this.clienteAtual.filtrarPorIdioma(idioma);
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
        if (this.clienteAtual == null) {
            System.out.println("Nenhum cliente logado");
            return new Stack<Série>();
        }
        return this.clienteAtual.filtrarPorQntEpisódios(quantidadeEpisódios);
    }

    /**
     * Busca uma série na plataforma de streaming pelo nome.
     * 
     * @param nomeSérie nome da série a ser buscada.
     * @return Série com o nome passado como parâmetro, NULL caso não exista.
     */
    public Série buscarSérie(int idSérie) {
        return this.séries.get(idSérie);
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
                    app.filtarPorGênero(System.console().readLine(" Gênero: ")).forEach(System.out::println);
                    break;
                case 4:
                    app.filtarPorIdioma(System.console().readLine(" Idioma: ")).forEach(System.out::println);
                    break;
                case 5:
                    app.filtarPorQntEpisódios(Utilitários.lerInt(" Quantidade de episódios: "))
                            .forEach(System.out::println);
                    break;
                case 6:
                    System.out.println(app);
                    break;
                case 7:
                    app.login(System.console().readLine(" Login: "));
                    break;
                case 8:
                    app.logOff();
                    break;
                case 9:
                    System.out.println(app.buscarSérie(
                            Utilitários.lerInt(" ID da série: ") //
                    ));
                    break;
                case 10:
                    break app;
                default:
                    System.out.println(" Opção inválida, tente novamente.");
            }
        if (System.console().readLine(" Deseja salvar os arquivos? (s/n)").contains("s"))
            app.salvarArquivos();
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

    /**************************************************************************************
     * 
     * 
     * 
     * ÁREA DE LEITURA E ESCRITA DE ARQUIVOS
     * 
     * 
     * 
     **************************************************************************************/

    /**
     * Lê os arquivos data/Audiência.csv, data/Séries.csv e data/Espectadores.csv e
     * os carrega na plataforma de streaming.
     * 
     * Espectadores são armazenados da forma: {Nome;Login;Senha}
     * 
     * Séries são armazenadas da forma: {IdSerie;Nome;DataDeLançamento}
     * 
     * Audiência é armazenada da forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * séries a assistir futuramente e “A” para séries já assistidas.
     */
    private void lerArquivos() {
        try {
            /**************** Espectadores ****************/
            Scanner leitor = new Scanner(new File("data/Espectadores.csv")).useDelimiter(";|\\n");

            while (leitor.hasNextLine())
                this.adicionarCliente(new Cliente(
                        leitor.next(), // Nome
                        leitor.next(), // Login
                        leitor.next() // Senha
                ));
            leitor.close();

            /**************** Séries ****************/
            leitor = new Scanner(new File("data/Séries.csv")).useDelimiter(";|\\n");

            while (leitor.hasNextLine())
                this.adicionarSérie(new Série(
                        Integer.parseInt(leitor.next()), // IdSerie
                        "Ação", // Gênero
                        leitor.next(), // Nome
                        "English", // Idioma
                        LocalDate.parse(leitor.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), // DataDeLançamento
                        10 // Quantidade de episódios
                ));
            leitor.close();

            /**************** Filmes ****************/
            leitor = new Scanner(new File("data/Filmes.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext())
                this.adicionarFilme(new Filme(
                        Integer.parseInt(leitor.next()), // IdSerie
                        "Ação", // Gênero
                        leitor.next(), // Nome
                        "English", // Idioma
                        LocalDate.parse(leitor.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), // DataDeLançamento
                        Integer.parseInt(leitor.next()) // duração
                ));
            leitor.close();

            /**************** Audiência ****************/
            leitor = new Scanner(new File("data/Audiência.csv")).useDelimiter(";|\\n");

            while (leitor.hasNextLine()) {
                this.clienteAtual = this.login(leitor.next());
                this.registrarAudiência(
                        leitor.next().charAt(0) == 'A', // Se a série já foi assistida
                        this.buscarSérie(
                                Integer.parseInt(leitor.next()) // Busca a série pelo ID
                        ) //
                );
            }
            this.logOff();
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println(" Arquivo data/Audiência.csv não encontrado.");
        }

    }

    /**
     * Escreve os arquivos data/Audiência.csv, data/Séries.csv e
     * data/Espectadores.csv e os carrega na plataforma de streaming.
     * 
     * Espectadores são armazenados da forma: {Nome;Login;Senha}
     * 
     * Séries são armazenadas da forma: {IdSerie;Nome;DataDeLançamento}
     * 
     * Audiência é armazenada da forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * séries a assistir futuramente e “A” para séries já assistidas.
     */
    private void salvarArquivos() {
        try {
            /**************** Espectadores ****************/
            FileWriter espectadores = new FileWriter("data/Espectadores.csv");
            for (Cliente cliente : this.clientes.values())
                espectadores.write(cliente + "\n");
            espectadores.close();

            /**************** Séries ****************/
            espectadores = new FileWriter("data/Séries.csv");
            for (Série série : this.séries.values())
                espectadores.write(série.toFile() + "\n");
            espectadores.close();

            /**************** Filmes ****************/
            espectadores = new FileWriter("data/Filmes.csv");
            for (Filme filme : this.filmes.values())
                espectadores.write(filme.toFile() + "\n");
            espectadores.close();

            /**************** Audiência ****************/
            espectadores = new FileWriter("data/Audiência.csv");
            for (Cliente cliente : this.clientes.values())
                for (String paraVerEJáVista : cliente.audiências())
                    espectadores.write(paraVerEJáVista + "\n");
            espectadores.close();
        } catch (IOException e) {
            System.out.println(" ERRO: Arquivo não encontrado." + e.getMessage());
        }
    }

}
