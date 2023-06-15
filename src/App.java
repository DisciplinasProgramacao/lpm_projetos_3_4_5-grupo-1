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
     * @param mensagem
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
     * Printa um menu de usuario no console.
     * 
     * @return opcao lida do console.
     */
    public static int menu() {
        return lerInt("\n 1 - Ler Arquivos\n" +
                " 2 - Salvar Arquivos\n" +
                " 3 - Filtrar por Genero\n" +
                " 4 - Filtrar por Idioma\n" +
                " 5 - Filtrar por Quantidade de Episodios\n" +
                " 6 - Filtrar filmes por duracao\n" +
                " 7 - Login\n" +
                " 8 - Logout\n" +
                " 9 - Adicionar Midia//Audiencia/Para Ver\n" +
                " 10 - Buscar Midia\n" +
                " 11 - Sair\n\n" +
                " Digite uma opcao: " //
        );
    }

    /**
     * Printa um menu de usuario no console voltado para adicionar midia.
     * 
     * @return opcao lida do console.
     */
    public static int menuAddString() {
        return lerInt("\n 1 - Adicionar Serie\n" +
                " 2 - Adicionar Filme\n" +
                " 3 - Adicionar \n" +
                " 4 - Adicionar Audiencia\n" +
                " 5 - Voltar\n\n" +
                " Digite uma opcao: " //
        );
    }

    /**
     * Opções do menu.
     */
    public static void menuAdd() {
        switch (App.menuAddString()) {
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
     * Metodo principal da aplicacao.
     * 
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        boolean lock = true;
        while (lock)
            switch (App.menu()) {
                // Ler arquivos
                case 1 -> app = LeitorEscritor.lerArquivos(app);

                // Salvar arquivos
                case 2 -> LeitorEscritor.escreverArquivos(
                        app.getClientes().values(),
                        app.getMidia().values() //
                    );

                // Filtrar midias por genero
                case 3 -> app.filtrarPorGenero(App.lerStr(" Genero: ")).forEach(System.out::println);

                // Filtrar midias por idioma
                case 4 -> app.filtrarPorIdioma(App.lerStr(" Idioma: ")).forEach(System.out::println);

                // Filtrar series por quantidade de episodios
                case 5 -> app.filtrarPorQntEpisodios(App.lerInt(" Quantidade de episodios: "))
                        .forEach(System.out::println);

                // Filtrar filmes por duracao
                case 6 -> app.filtrarPorDuracao(App.lerInt(" Duracao: ")).forEach(System.out::println);

                // Login
                case 7 -> app.login(
                        App.lerStr(" Login: "),
                        App.lerStr(" Senha: "),
                        false // Não e administrador
                    );

                // Logoff
                case 8 -> app.logOff();

                // Menu de adicao
                case 9 -> App.menuAdd();

                // Buscar midia
                case 10 -> System.out.println(app.buscarMidia(
                        App.lerStr(" Nome da midia: ") //
                    ));

                case 11 -> lock = false;

                default -> System.out.println(" Opcao invalida, tente novamente.");
            }

        if (App.lerStr(" Deseja salvar os arquivos? (s/n)").contains("s"))
            LeitorEscritor.escreverArquivos(
                    app.getClientes().values(),
                    app.getMidia().values() //
            );
    }

}
