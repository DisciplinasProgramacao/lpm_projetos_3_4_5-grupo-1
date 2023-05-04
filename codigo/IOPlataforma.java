import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;

/**
 * Classe usado para ler e escrever os arquivos csv da plataforma de streaming
 */
public class IOPlataforma {

    /**
     * Construtor vazio.
     */
    private IOPlataforma() {
        throw new Error("Classe não instanciável");
    }

    /**
     * Lê os arquivos data/Audiência.csv, data/Séries.csv e data/Espectadores.csv e
     * os carrega na plataforma de streaming.
     * 
     * Espectadores são armazenados na forma: {Nome;Login;Senha}
     * 
     * Séries são armazenadas na forma: {IdSerie;Nome;DataDeLançamento}
     * 
     * Audiência é armazenada na forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * séries a assistir futuramente e “A” para séries já assistidas.
     * 
     * @param app plataforma de streaming com ou sem dados
     * @return plataforma de streaming com os dados já existentes + dados lidos
     */
    public static PlataformaStreaming lerArquivos(PlataformaStreaming app) {
        try {
            /**************** Espectadores ****************/
            Scanner leitor = new Scanner(new File("data/Espectadores.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext())
                app.adicionarCliente(new Cliente(
                        leitor.next(), // Nome
                        leitor.next(), // Login
                        leitor.next() // Senha
                ));
            leitor.close();

            /**************** Séries ****************/
            leitor = new Scanner(new File("data/Séries.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext()) {
                app.adicionarSérie(new Série(
                        Integer.parseInt(leitor.next()), // IdSerie
                        Mídia.GENÊROS[(int) Math.floor(Math.random() * Mídia.GENÊROS.length)], // Gênero
                        leitor.next(), // Nome
                        "Português", // Idioma
                        LocalDate.parse(leitor.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), // DataDeLançamento
                        10 // Quantidade de episódios
                ));
            }
            leitor.close();

            /**************** Filmes ****************/
            leitor = new Scanner(new File("data/Filmes.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext())
                app.adicionarFilme(new Filme(
                        Integer.parseInt(leitor.next()), // IdSerie
                        Mídia.GENÊROS[(int) Math.floor(Math.random() * Mídia.GENÊROS.length)], // Gênero
                        leitor.next(), // Nome
                        "Português", // Idioma
                        LocalDate.parse(leitor.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), // DataDeLançamento
                        Integer.parseInt(leitor.next()) // duração
                ));
            leitor.close();

            /**************** Audiência ****************/
            leitor = new Scanner(new File("data/Audiência.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext()) {
                app.login(leitor.next(), null, true);
                app.registrarAudiência(
                        leitor.next().charAt(0) == 'A', // Se a série já foi assistida
                        app.buscarMídia(
                                Integer.parseInt(leitor.next()) // Busca a série pelo ID
                        ) //
                );
            }
            app.logOff();
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println(" Arquivo data/Audiência.csv não encontrado.");
        }

        return app;
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
     * 
     * @param clientes Lista de clientes
     * @param séries   Lista de séries
     * @param filmes   Lista de filmes
     */
    public static void salvarArquivos(Collection<Cliente> clientes, Collection<Série> séries,
            Collection<Filme> filmes) {
        try {
            /**************** Espectadores ****************/
            FileWriter espectadores = new FileWriter("data/Espectadores.csv");
            for (Cliente cliente : clientes)
                espectadores.write(cliente + "\n");
            espectadores.close();

            /**************** Séries ****************/
            espectadores = new FileWriter("data/Séries.csv");
            for (Série série : séries)
                espectadores.write(série.toFile() + "\n");
            espectadores.close();

            /**************** Filmes ****************/
            espectadores = new FileWriter("data/Filmes.csv");
            for (Filme filme : filmes)
                espectadores.write(filme.toFile() + "\n");
            espectadores.close();

            /**************** Audiência ****************/
            espectadores = new FileWriter("data/Audiência.csv");
            for (Cliente cliente : clientes)
                for (String paraVerEJáVista : cliente.audiências())
                    espectadores.write(paraVerEJáVista + "\n");
            espectadores.close();
        } catch (IOException e) {
            System.out.println(" ERRO: Arquivo não encontrado." + e.getMessage());
        }
    }

}
