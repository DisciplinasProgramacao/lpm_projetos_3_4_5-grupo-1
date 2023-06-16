import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Executa a aplicacao.
 */
public class App {

    /** Plataforma de streaming e seus nome */
    private static PlataformaStreaming app = new PlataformaStreaming("Plataforma");

    /** Construtor não instanciável */
    private App() {
        throw new InstantiationError("Classe não instanciável");
    }

    /**
     * Metodo que le uma string do console.
     * 
     * @param mensagem a ser exibida ao usuario.
     * @return string lida do console.
     */
    public static String lerStr(String mensagem) {
        return System.console().readLine(mensagem);
    }

    /**
     * Metodo que le um inteiro do console.
     * 
     * @param mensagem a ser exibida ao usuario.
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
     * Printa um menu de usuario no console voltado a leiura de relatorios.
     * 
     * @return opcao lida do console.
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

            case 3 -> System.out.println(" " + app.porcentagemClientesCom15Avaliacoes());

            case 4 -> {
                if (App.lerStr("Deseja filtrar por genero?").equals("s"))
                    app.melhoresAvaliacoes(App.lerStr("Genero: ")).forEach(System.out::println);
                else
                    app.melhoresAvaliacoes().forEach(System.out::println);
            }

            case 5 -> {
                if (App.lerStr("Deseja filtrar por genero?").equals("s"))
                    app.maisVisualizadas(App.lerStr("Genero: ")).forEach(System.out::println);
                else
                    app.maisVisualizadas().forEach(System.out::println);
            }
        }
    }

    /**
     * Printa um menu de usuario no console voltado a leiura de filtros.
     * 
     * @return opcao lida do console.
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
            case 1 -> app.filtrarPorGenero(App.lerStr(" Genero: ")).forEach(System.out::println);

            // Filtrar midias por idioma
            case 2 -> app.filtrarPorIdioma(App.lerStr(" Idioma: ")).forEach(System.out::println);

            // Filtrar series por quantidade de episodios
            case 3 -> app.filtrarPorQntEpisodios(App.lerInt(" Quantidade de episodios: "))
                    .forEach(System.out::println);

            // Filtrar filmes por duracao
            case 4 -> app.filtrarPorDuracao(App.lerInt(" Duracao: ")).forEach(System.out::println);
        }
    }

    /**
     * Printa um menu de usuario no console voltado para adicionar midia.
     * 
     * @return opcao lida do console.
     */
    public static int menuAddPrint() {
        return lerInt("\n 1 - Adicionar Serie\n" +
                " 2 - Adicionar Filme\n" +
                " 3 - Adicionar Cliente\n" +
                " 4 - Adicionar Audiencia\n" +
                " 5 - Voltar" +
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
                    App.lerStr(" A midia ja foi assistida? (s/n) ").contains("s"),
                    app.buscarMidia(App.lerInt(" ID da midia: ")),
                    true // Perguntar nota
                );

            // Opcao invalida
            default -> System.out.println(" Opcao invalida, tente novamente.");

            // Voltar
            case 5 -> {
            }
        }
    }

    /**
     * Printa um menu de usuario no console.
     * 
     * @return opcao lida do console.
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

                // Relatorios
                case 8 -> {
                    if (app.getClienteAtual().isPresent())
                        System.out.println(" Voce não pode estar logado como cliente e acessar os relatorios.");
                    else
                        App.menuRelatorios();
                }

                case 0 -> lock = false;

                default -> System.out.println(" Opcao invalida, tente novamente.");
            }
    }

    /**
     * Metodo principal da aplicacao.
     * 
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {

        // Menu
        App.menu();

        if (App.lerStr(" Deseja salvar os arquivos? (s/n)").contains("s"))
            LeitorEscritor.escreverArquivos(
                    app.getClientes().values(),
                    app.getMidia().values() //
            );
    }

}
