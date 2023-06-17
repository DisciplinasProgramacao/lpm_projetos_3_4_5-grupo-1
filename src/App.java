import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Executa a aplicação.
 */
public class App {

    /** Plataforma de streaming singleton */
    private static PlataformaStreaming app = PlataformaStreaming.getInstance();

    /** Construtor não instanciável */
    private App() {
        throw new InstantiationError("Classe não instanciável");
    }

    /**
     * Meéodo que lê uma string do console através do {@link System#console()}.
     * 
     * @param mensagem a ser exibida ao usuário.
     * @return string lida do console.
     */
    public static String lerStr(String mensagem) {
        return System.console().readLine(mensagem);
    }

    /**
     * Meéodo que lê um inteiro do console.
     * 
     * @param mensagem a ser exibida ao usuário.
     * @return inteiro lido do console.
     */
    public static int lerInt(String mensagem) {
        try {
            return Integer.parseInt(App.lerStr(mensagem));
        } catch (NumberFormatException e) {
            return lerInt(" ERRO: Valor invalido. Digite um numero inteiro: ");
        }
    }

    /**
     * Printa um menu de usuário no console voltado a leiura de relatórios.
     * 
     * @return opção lida do console.
     */
    public static int menuRelatoriosPrint() {
        return lerInt("\n 1 - Mostrar cliente viciado\n" +
                " 2 - Mostrar maior avaliador\n" +
                " 3 - Mostrar a porcentagem dos clientes com pelo menos 15 avaliações\n" +
                " 4 - Mostrar as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente\n" +
                " 5 - Mostrar as 10 mídias com mais visualizações, em ordem decrescente\n" +
                " 0 - Voltar" +
                "\n\n Digite uma opcao: " //
        );
    }

    /**
     * Opções para o menu acima.
     */
    public static void menuRelatorios() {
        switch (App.menuRelatoriosPrint()) {
            case 1 -> System.out.println(" " + app.clienteViciado());

            case 2 -> System.out.println(" " + app.maiorAvaliador());

            case 3 -> System.out.println(" " + app.clientesCom15Avaliacoes());

            case 4 -> {
                if (App.lerStr(" Deseja filtrar por genero? (s/n) ").toLowerCase().contains("s"))
                    app.melhoresAvaliacoes(App.lerStr(Genero.getGeneros() + " Genero: ").toUpperCase())
                            .forEach(System.out::println);
                else
                    app.melhoresAvaliacoes().forEach(System.out::println);
            }

            case 5 -> {
                if (App.lerStr(" Deseja filtrar por genero? (s/n) ").toLowerCase().contains("s"))
                    app.maisVisualizadas(App.lerStr(Genero.getGeneros() + " Genero: ").toUpperCase())
                            .forEach(System.out::println);
                else
                    app.maisVisualizadas().forEach(System.out::println);
            }
        }
    }

    /**
     * Printa um menu de usuário no console voltado a leiura de filtros.
     * 
     * @return opção lida do console.
     */
    public static int menuFiltrosPrint() {
        return lerInt("\n 1 - Filtrar por Genero\n" +
                " 2 - Filtrar por Idioma\n" +
                " 3 - Filtrar por Quantidade de Episodios\n" +
                " 4 - Filtrar filmes por duracao\n" +
                " 0 - Voltar" +
                "\n\n Digite uma opcao: " //
        );
    }

    /**
     * Opções para o menu acima.
     */
    public static void menuFiltros() {
        switch (App.menuFiltrosPrint()) {
            // Filtrar midias por genero
            case 1 -> app.filtrarPorGenero(App.lerStr(Genero.getGeneros() +" Genero: ")).forEach(System.out::println);

            // Filtrar midias por idioma
            case 2 -> app.filtrarPorIdioma(App.lerStr(Idioma.getIdiomas() +" Idioma: ")).forEach(System.out::println);

            // Filtrar series por quantidade de episodios
            case 3 -> app.filtrarPorQntEpisodios(App.lerInt(" Quantidade de episodios: "))
                    .forEach(System.out::println);

            // Filtrar filmes por duracao
            case 4 -> app.filtrarPorDuracao(App.lerInt(" Duracao: ")).forEach(System.out::println);

            // Opcão invalida
            default -> System.out.println(" Opcao invalida, tente novamente.");

            // Voltar
            case 0 -> {
            }
        }
    }

    /**
     * Printa um menu de usuário no console voltado para adicionar midia.
     * 
     * @return opção lida do console.
     */
    public static int menuAddPrint() {
        return lerInt("\n 1 - Adicionar Serie\n" +
                " 2 - Adicionar Filme\n" +
                " 3 - Adicionar Cliente\n" +
                " 4 - Adicionar Audiencia\n" +
                " 5 - Adicionar Avaliacao\n" +
                " 0 - Voltar" +
                "\n\n Digite uma opcao: " //
        );
    }

    /**
     * Opções para o menu acima.
     */
    public static void menuAdd() {
        switch (App.menuAddPrint()) {
            // Adicionar serie
            case 1 -> app.adicionarMidia(new Serie(
                    App.lerInt(" ID: "), // ID
                    Genero.sortearGenero().getNome(), // Genero
                    App.lerStr(" Nome: "), // Nome
                    "Portugues", // Idioma
                    LocalDate.parse(
                            App.lerStr(" Data de lançamento (dd/MM/yyyy): "),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy") // Data de lançamento
                    ),
                    10 // Quantidade de episodios
                ));

            // Adicionar filme
            case 2 -> app.adicionarMidia(new Filme(
                    App.lerInt(" ID: "), // ID
                    Genero.sortearGenero().getNome(), // Genero
                    App.lerStr(" Nome: "), // Nome
                    "Portugues", // Idioma
                    LocalDate.parse(
                            App.lerStr(" Data de lançamento (dd/MM/yyyy): "),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy") // Data de lançamento
                    ),
                    App.lerInt(" Duracao (min): ") // Duracao
                ));

            // Adicionar cliente
            case 3 -> app.adicionarCliente(new Cliente(
                    App.lerStr(" Nome: "), // Nome
                    App.lerStr(" Login: "), // Login
                    App.lerStr(" Senha: ") // Senha
                ));

            // Adicionar audiencia
            case 4 -> app.registrarAudiencia(
                    App.lerStr(" A midia ja foi assistida? (s/n) ").toLowerCase().contains("s"),
                    app.buscarMidia(App.lerInt(" ID da midia: ")) //
                );

            // Adicionar avaliacao
            case 5 -> app.registrarAvaliacao(
                    app.buscarMidia(App.lerInt(" ID da midia: ")),
                    lerInt(" Nota: ") //
                );

            // Opção invalida
            default -> System.out.println(" Opcao invalida, tente novamente.");

            // Voltar
            case 0 -> {
            }
        }
    }

    /**
     * Printa um menu de usuário no console.
     * 
     * @return opção lida do console.
     */
    public static int menuPrint() {
        return lerInt("\n 1 - Ler Arquivos\n" +
                " 2 - Salvar Arquivos\n" +
                " 3 - Login\n" +
                " 4 - Logout\n" +
                " 5 - Buscar Midia\n" +
                " 6 - Adicionar Midia/Audiencia/Cliente\n" +
                " 7 - Acessar filtros\n" +
                " 8 - Acessar relatorios\n" +
                " 0 - Sair\n\n" +
                " Digite uma opcao: " //
        );
    }

    /**
     * Opções para o menu acima.
     */
    public static void menu() {
        boolean lock = true;
        while (lock)
            switch (App.menuPrint()) {
                // Ler arquivos
                case 1 -> app = LeitorEscritor.lerArquivos(app);

                // Salvar arquivos
                case 2 -> LeitorEscritor.escreverArquivos(
                        app.getClientes().values(),
                        app.getMidia().values() //
                    );

                // Login
                case 3 -> app.login(
                        App.lerStr(" Login: "),
                        App.lerStr(" Senha: "),
                        false // Não e administrador
                    );

                // Logoff
                case 4 -> app.logOff();

                // Buscar midia
                case 5 -> System.out.println(app.buscarMidia(App.lerStr(" Nome da midia: ")));

                // Menu de adicao
                case 6 -> App.menuAdd();

                // Filtrar midias por genero
                case 7 -> App.menuFiltros();

                // Relatórios
                case 8 -> {
                    if (app.getClienteAtual().isPresent())
                        System.out.println(" Voce não pode estar logado como cliente e acessar os relatórios.");
                    else
                        App.menuRelatorios();
                }

                case 0 -> lock = false;

                default -> System.out.println(" Opcao invalida, tente novamente.");
            }
    }

    /**
     * Meéodo principal da aplicacao.
     * 
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {

        // Menu
        App.menu();

        if (App.lerStr(" Deseja salvar os arquivos? (s/n)").toLowerCase().contains("s"))
            LeitorEscritor.escreverArquivos(
                    app.getClientes().values(),
                    app.getMidia().values() //
            );
    }

}
