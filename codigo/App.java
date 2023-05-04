import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        PlataformaStreaming app = new PlataformaStreaming("Netflix");
        app: while (true)
            switch (Utilitários.menu()) {
                case 1: // Ler arquivos
                    app = IOPlataforma.lerArquivos(app);
                    break;
                case 2: // Salvar arquivos
                    IOPlataforma.salvarArquivos(
                            app.getClientes().values(),
                            app.getSéries().values(),
                            app.getFilmes().values() //
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
                    app.getClientes().values(),
                    app.getSéries().values(),
                    app.getFilmes().values() //
            );
    }

}
