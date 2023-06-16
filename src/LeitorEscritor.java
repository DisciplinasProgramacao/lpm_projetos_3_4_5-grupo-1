import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;

/**
 * Classe usado para ler e escrever os arquivos csv da plataforma de streaming,
 * não instanciável.
 */
final public class LeitorEscritor {

    /**
     * Construtor vazio.
     */
    private LeitorEscritor() {
        throw new InstantiationError("Classe não instanciável");
    }

    /**
     * Le os arquivos data/Audiencia.csv, data/Series.csv e data/Espectadores.csv e
     * os carrega na plataforma de streaming.
     * 
     * Espectadores são armazenados na forma: {Nome;Login;Senha}
     * 
     * Series são armazenadas na forma: {IdSerie;Nome;DataDeLançamento}
     * 
     * Audiencia e armazenada na forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * series a assistir futuramente e “A” para series já assistidas.
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

            /**************** Series ****************/
            leitor = new Scanner(new File("data/Series.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext()) {
                app.adicionarMidia(new Serie(
                        Integer.parseInt(leitor.next()), // IdSerie
                        leitor.next(), // Nome
                        LocalDate.parse(leitor.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) // DataDeLançamento
                ));
            }
            leitor.close();

            /**************** Filmes ****************/
            leitor = new Scanner(new File("data/Filmes.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext())
                app.adicionarMidia(new Filme(
                        Integer.parseInt(leitor.next()), // IdSerie
                        leitor.next(), // Nome
                        LocalDate.parse(leitor.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), // DataDeLançamento
                        Integer.parseInt(leitor.next()) // duração
                ));
            leitor.close();

            /**************** Audiencia ****************/
            leitor = new Scanner(new File("data/Audiencia.csv")).useDelimiter(";|\\n");

            while (leitor.hasNext()) {
                app.login(leitor.next(), null, true);
                app.registrarAudiencia(
                        leitor.next().charAt(0) == 'A', // Se a serie já foi assistida
                        app.buscarMidia(
                                Integer.parseInt(leitor.next()) // Busca a serie pelo ID
                        ),
                        false // Nao ha nota na leiura do arquivo
                );
            }
            app.logOff();
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println(" Arquivo data/Audiencia.csv não encontrado.");
        }

        return app;
    }

    /**
     * Escreve os arquivos data/Audiencia.csv, data/Series.csv e
     * data/Espectadores.csv e os carrega na plataforma de streaming.
     * 
     * Espectadores são armazenados da forma: {Nome;Login;Senha}
     * 
     * Series são armazenadas da forma: {IdSerie;Nome;DataDeLançamento}
     * 
     * Audiencia e armazenada da forma: {Login;F/A;IdSerie}, sendo “F” para lista de
     * series a assistir futuramente e “A” para series já assistidas.
     * 
     * @param clientes coleção de clientes da plataforma de streaming
     * @param midias   coleção de midias da plataforma de streaming
     */
    public static void escreverArquivos(Collection<Cliente> clientes, Collection<Midia> midias) {
        try {
            /**************** Espectadores ****************/
            FileWriter espectadores = new FileWriter("data/Espectadores.csv");

            for (Cliente cliente : clientes)
                espectadores.write(cliente + "\n");
            espectadores.close();

            /**************** Midia ****************/
            FileWriter series = new FileWriter("data/Series.csv");
            FileWriter filmes = new FileWriter("data/Filmes.csv");
            for (Midia midia : midias)
                if (midia instanceof Filme)
                    filmes.write(midia.toFile() + "\n");
                else
                    series.write(midia.toFile() + "\n");
            series.close();
            filmes.close();

            /**************** Audiencia ****************/
            FileWriter audiencia = new FileWriter("data/Audiencia.csv");
            for (Cliente cliente : clientes)
                for (String paraVerEJáVista : cliente.audiencias())
                    audiencia.write(paraVerEJáVista + "\n");
            audiencia.close();
        } catch (IOException e) {
            System.out.println(" ERRO: Arquivo não encontrado." + e.getMessage());
        }
    }

}
