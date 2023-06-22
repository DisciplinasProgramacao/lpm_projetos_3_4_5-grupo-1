import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        throw new InstantiationError("Classe nao instanciavel");
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
            Fabrica fabrica = new FabricaCliente();
            /**************** Espectadores ****************/
            Scanner leitor = new Scanner(new File(Cliente.ARQUIVO)).useDelimiter(";|\\n");

            while (leitor.hasNext())
                app.adicionarCliente((Cliente) fabrica.criar(
                        new String[] { leitor.next(), leitor.next(), leitor.next() } //
                ));
            leitor.close();

            fabrica = new FabricaSerie();
            /**************** Series ****************/
            leitor = new Scanner(new File(Serie.ARQUIVO)).useDelimiter(";|\\n");

            while (leitor.hasNext()) {
                app.adicionarMidia((Serie) fabrica.criar(
                        new String[] { leitor.next(), leitor.next(), leitor.next() } //
                ));
            }
            leitor.close();

            fabrica = new FabricaFilme();
            /**************** Filmes ****************/
            leitor = new Scanner(new File(Filme.ARQUIVO)).useDelimiter(";|\\n");

            while (leitor.hasNext())
                app.adicionarMidia((Filme) fabrica.criar(
                        new String[] { leitor.next(), leitor.next(), leitor.next(), leitor.next() } //
                ));
            leitor.close();

            /**************** Audiencia ****************/
            leitor = new Scanner(new File(Midia.ARQUIVO)).useDelimiter(";|\\n");

            while (leitor.hasNext()) {
                app.login(leitor.next(), null, true);
                app.registrarAudiencia(
                        leitor.next().charAt(0) == 'A', // Se a serie já foi assistida
                        app.buscarMidia(
                                Integer.parseInt(leitor.next()) // Busca a serie pelo ID
                        ) //
                );
            }
            app.logOff();
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println(" ERRO: Arquivo nao encontrado." + e.getMessage());
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
            Fabrica fabrica = new FabricaCliente();
            fabrica.escrever(clientes.stream().map(cliente -> (Object) cliente));
            fabrica = new FabricaSerie();
            fabrica.escrever(midias.stream().filter(midia -> midia instanceof Serie).map(midia -> (Object) midia));
            fabrica = new FabricaFilme();
            fabrica.escrever(midias.stream().filter(midia -> midia instanceof Filme).map(midia -> (Object) midia));

            /**************** Audiencia ****************/
            FileWriter audiencia = new FileWriter("data/Audiencia.csv");
            for (Cliente cliente : clientes)
                for (String paraVerEJáVista : cliente.audiencias())
                    audiencia.write(paraVerEJáVista + "\n");
            audiencia.close();
        } catch (IOException e) {
            System.out.println(" ERRO: Arquivo nao encontrado." + e.getMessage());
        }
    }

}
