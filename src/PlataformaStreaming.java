import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Clase que representa uma plataforma de streaming.
 */
public class PlataformaStreaming {

    /** Nome da plataforma de streaming */
    private String nome;

    /** Midias da plataforma */
    private HashMap<Integer, Midia> midias;

    /** Mapa que permite a busca de midias pelo nome */
    private HashMap<String, Integer> nomes;

    /** Clientes da plataforma */
    private HashMap<String, Cliente> clientes;

    /** Cliente logado atualmente na plataforma */
    private Optional<Cliente> clienteAtual;

    /** Instancia unica da plataforma de streaming */
    private static PlataformaStreaming instance;

    /**
     * Construtor da classe PlataformaStreaming.
     * 
     * @param nome Nome da plataforma de streaming.
     */
    private PlataformaStreaming(String nome) {
        this.nome = nome;
        this.midias = new HashMap<Integer, Midia>();
        this.nomes = new HashMap<String, Integer>();
        this.clientes = new HashMap<String, Cliente>();
        this.logOff();
    }

    /**
     * Padrão de projeto Singleton.
     * 
     * @return instancia unica da plataforma de streaming.
     */
    public static PlataformaStreaming getInstance() {
        if (instance == null)
            instance = new PlataformaStreaming("Platform");
        return instance;
    }

    /**
     * Executa o login de um cliente.
     * 
     * @param user  nome de usuario do cliente.
     * @param senha senha do cliente.
     * @param admin se o login ocorre como administrador.
     */
    public void login(String user, String senha, boolean admin) {
        try {
            if (admin) // loga no cliente como administrador
                this.clienteAtual = Optional.of(this.clientes.get(user));

            else if (this.clientes.get(user).loginPassword(senha)) { // loga no cliente como usuario comum
                this.clienteAtual = Optional.of(this.clientes.get(user));
                System.out.println(" Logado como " + this.clienteAtual.get().getLogin());
            }

            else // login ou senha incorretos
                System.out.println("Login ou senha incorretos");

        } catch (NullPointerException e) { // cliente nao encontrado, excecao na linha 45
            System.out.println(" Nenhum cliente com esse login");
        }
    }

    /**
     * Desloga o cliente atual.
     */
    public void logOff() {
        this.clienteAtual = Optional.empty();
    }

    /**
     * Adiciona uma midias na plataforma de streaming.
     * 
     * @param midias nova midias a ser adicionada.
     */
    public void adicionarMidia(Midia midias) {
        this.midias.put(midias.getID(), midias);
        this.nomes.put(midias.getNome(), midias.getID());
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
     * Adiciona uma midia a lista de midias ja assistidas pelo cliente atual.
     * 
     * @param completado se o cliente assistiu a midia por completo.
     * @param midia      a ser adicionada a lista de midias assistidas.
     */ // @formatter:off
    public void registrarAudiencia(boolean completado, Midia midia) {
        if (midia == null) { // Se a midia nao existir, nao e possivel registrar audiencia
            System.out.println("Midia nao encontrada"); return;
        }

        if (this.clienteAtual.isEmpty()) { // Se nao houver cliente logado, nao e possivel registrar audiencia
            System.out.println("Nenhum cliente logado, nao e possivel registrar audiencia."); return;
        }

        if (!completado) { // Se o cliente nao assistiu a midia por completo, adiciona a midia a lista de midias para assistir
            this.clienteAtual.get().adicionarNaLista(midia); return;
        }

        // Registra a audiencia da midia. Se o cliente se tornar especialista, atualiza a referencia ao cliente @formatter:on
        this.clienteAtual.get().registrarAudiencia(midia);
    }

    /**
     * Adiciona uma avaliacao a midia caso o cliente ja tenha assistido a midia.
     * 
     * @param midia     a ter uma avaliacao adicionada.
     * @param avaliacao define se o cliente avaliou a midia ou nao.
     */ // @formatter:off
    public void registrarAvaliacao(Midia midia, int avaliacao) {
        if (midia == null) { // Se a midia nao existir, nao e possivel registrar avaliacao
            System.out.println("Midia nao encontrada"); return;
        }

        if (this.clienteAtual.isEmpty()) { // Se nao houver cliente logado, nao e possivel registrar avaliacao
            System.out.println("Nenhum cliente logado, nao e possivel registrar avaliacao."); return;
        }

        // Registra a avaliacao da midia. Se o cliente se tornar especialista, atualiza a referencia ao cliente @formatter:on
        if (this.clienteAtual.get().getAvaliacoes().midiaAvaliada(midia.getID())) {
            clienteAtual.get().registrarAvaliacao(
                    midia,
                    avaliacao,
                    LocalDate.now() //
            );
            return;
        }

        System.out.println("Midia nao assistida, nao e possivel avaliar");
    }

    /**
     * Retorna uma lista com todas as midias de um cliente participantes de um
     * determinado genero.
     * 
     * @param genero genero das midias a serem filtradas
     * @return Lista com as midias do genero passado como parâmetro
     */
    public Stream<String> filtrarPorGenero(String genero) {
        if (this.clienteAtual.isPresent()) // Se o cliente estiver logado, filtra as midias dele
            return this.clienteAtual.get().filtrarPorGenero(genero);

        // Se nao, filtra todas as midias da plataforma
        return this.midias.values().stream() // Stream<Midia>
                .filter(midia -> midia.getGenero().equals(genero)) // Stream<Midia> filtrada por genero
                .map(Object::toString); // Stream<String> com as midias filtradas para impressão
    }

    /**
     * Retorna uma lista com todas as midias de um cliente que possuem um
     * determinado idioma.
     * 
     * @param idioma idioma das midias a serem filtradas
     * @return Lista com as midias do idioma passado como parâmetro
     */
    public Stream<String> filtrarPorIdioma(String idioma) {
        if (this.clienteAtual.isPresent()) // Se o cliente estiver logado, filtra as midias dele
            return this.clienteAtual.get().filtrarPorIdioma(idioma);

        // Se nao, filtra todas as midias da plataforma
        return this.midias.values().stream() // Stream<Midia>
                .filter(midia -> midia.getIdioma().equals(idioma)) // Stream<Midia> filtrada por idioma
                .map(Object::toString); // Stream<String> com as midias filtradas para impressão
    }

    /**
     * Retorna uma lista com todas as series de um cliente que possuem uma
     * determinada quantidade de episodios.
     * 
     * @param quantidadeEpisodios quantidade de episodios das series a serem
     *                            filtrados
     * @return Lista com as series com a quantidade de episodios passada como
     *         parâmetro
     */
    public Stream<String> filtrarPorQntEpisodios(int quantidadeEpisodios) {
        if (this.clienteAtual.isPresent()) // Se o cliente estiver logado, filtra as series dele
            return this.clienteAtual.get().filtrarPorQntEpisodios(quantidadeEpisodios);

        // Se nao, filtra todas as midias da plataforma
        return this.midias.values().stream() // Stream<Midia>
                .filter(midia -> midia.getQntEp() == quantidadeEpisodios) // Stream<Midia> filtrada por qntEp
                .map(Object::toString); // Stream<String> com as series filtradas para impressão
    }

    /**
     * Retorna uma lista com todos os filmes de um cliente que possuem uma
     * determinada duracao.
     * 
     * @param duracao duracao dos filmes a serem filtrados
     * @return Lista com os filmes com a duracao passada como parâmetro
     */
    public Stream<String> filtrarPorDuracao(int duracao) {
        if (this.clienteAtual.isPresent()) // Se o cliente estiver logado, filtra os filmes dele
            return this.clienteAtual.get().filtrarPorDuracao(duracao);

        // Se nao, filtra todos os filmes da plataforma
        return this.midias.values().stream() // Stream<Midia>
                .filter(midia -> midia.getDuracao() == duracao) // Stream<Midia> filtrada por duracao
                .map(Object::toString); // Stream<String> com os filmes filtrados para impressão
    }

    /**
     * Retorna o cliente que assistiu mais midias na plataforma.
     * 
     * @return cliente viciado
     */
    public Cliente clienteViciado() {
        return this.clientes.values().stream() // Stream<Cliente>
                .max(Comparator.comparingInt(Cliente::getQntMidiasAssistidas)) // Cliente com mais midias assistidas
                .orElse(null); // Se nao houver cliente, retorna null
    }

    /**
     * Retorna o cliente que avaliou mais midias na plataforma.
     * 
     * @return cliente viciado
     */
    public Cliente maiorAvaliador() {
        return this.clientes.values().stream() // Stream<Cliente>
                .max(Comparator.comparingInt(cliente -> cliente.getAvaliacoes().total())) // Cliente com mais avaliacoes
                .orElse(null); // Se nao houver cliente, retorna null
    }

    /**
     * Retorna a porcentagem de clientes que avaliaram pelo menos 15 midias.
     * 
     * @return porcentagem de clientes.
     */
    public double clientesCom15Avaliacoes() {
        return this.clientes.values().stream() // Stream<Cliente>
                .filter(cliente -> cliente.getAvaliacoes().total() >= 15) // Stream<Cliente> filtrada
                .count() // Total de clientes
                / this.clientes.size() * 100.0; // Porcentagem
    }

    /**
     * Retorna as 10 mídias de melhor avaliação, com pelo menos 2 avaliações, em
     * ordem decrescente.
     * 
     * @return Stream com 10 melhores midias.
     */
    public Stream<String> melhoresAvaliacoes() {
        return this.midias.values().stream() // Stream<Midia>
                .filter(midia -> midia.getQntAvaliacoes() >= 2) // Stream<Midia> filtrada
                .sorted(Comparator.comparingInt(Midia::getRatingMedio)) // Stream<Midia> ordenada por rating
                .limit(10) // Stream<Midia> com apenas 10 elementos, os 10 melhores
                .map(Object::toString); // Stream<String> para impressão
    }

    /**
     * Retorna as 10 mídias de um determinado genero com melhor avaliação, com pelo
     * menos 100 avaliações, em ordem decrescente.
     * 
     * @param genero genero das midias a serem filtradas
     * @return Stream com 10 melhores midias.
     */ // @formatter:off
    public Stream<String> melhoresAvaliacoes(String genero) {
        return this.midias.values().stream() // Stream<Midia>
                .filter(midia -> midia.getQntAvaliacoes() >= 100 && midia.getGenero().equals(genero)) // Stream<Midia> filtrada por genero e qntAvaliacoes maior ou igual a 100
                .sorted(Comparator.comparingInt(Midia::getRatingMedio)) // Stream<Midia> ordenada por rating
                .limit(10) // Stream<Midia> com apenas 10 elementos, os 10 melhores
                .map(Object::toString); // Stream<String> para impressão
    } // @formatter:on

    /**
     * Retorna as 10 mídias com mais visualizações, em ordem decrescente.
     * 
     * @return Stream com 10 midias mais visualizadas.
     */
    public Stream<String> maisVisualizadas() {
        return this.midias.values().stream() // Stream<Midia>
                .sorted(Comparator.comparingInt(Midia::getAudiencia)) // Stream<Midia> ordenada por audiencia
                .limit(10) // Stream<Midia> com apenas 10 elementos, os 10 melhores
                .map(Object::toString); // Stream<String> para impressão
    }

    /**
     * Retorna as 10 mídias de um determinado genero com mais visualizações, em
     * ordem decrescente.
     * 
     * @param genero genero das midias a serem filtradas
     * @return Stream com 10 midias mais visualizadas.
     */
    public Stream<String> maisVisualizadas(String genero) {
        return this.midias.values().stream() // Stream<Midia>
                .filter(midia -> midia.getGenero().equals(genero)) // Stream<Midia> filtrada
                .sorted(Comparator.comparingInt(Midia::getAudiencia)) // Stream<Midia> ordenada por audiencia
                .limit(10) // Stream<Midia> com apenas 10 elementos, os 10 melhores
                .map(Object::toString); // Stream<String> para impressão
    }

    /**
     * Busca uma midia na plataforma de streaming pelo id.
     * 
     * @param idMidia id da midia a ser buscada.
     * @return IMidia com o id passado como parâmetro, NULL caso nao exista.
     */
    public Midia buscarMidia(int idMidia) {
        return this.midias.get(idMidia);
    }

    /**
     * Busca uma midia na plataforma de streaming pelo nome.
     * 
     * @param nomeMidia nome da midia a ser buscada.
     * @return IMidia com o nome passado como parâmetro, NULL caso nao exista.
     */ // @formatter:off
    public String buscarMidia(String nomeMidia) {
        try {
            return this.midias.get(this.nomes.get(nomeMidia)) +
                    (this.clienteAtual.isPresent()
                            ? " Sua avaliação: " + String.valueOf(this.clienteAtual.get().getAvaliacao(this.nomes.get(nomeMidia)))
                            : "");
        } catch (NullPointerException e) {
            return "NULL";
        }
    }

    /**
     * Retorna uma string que representa a plataforma de streaming com o nome e
     * número de midias e clientes cadastrados.
     * 
     * @return String que representa a plataforma de streaming.
     */
    @Override
    public String toString() {
        return " Ha " + this.midias.size() + " midias cadastradas na plataforma " + this.nome + " e " + this.clientes.size() + " clientes cadastrados.";
    }

    /** Retorna as midias
     * @return midias*/
    public HashMap<Integer, Midia> getMidia() { return this.midias; }

    /** Retorna os clientes
     * @return clientes*/
    public HashMap<String, Cliente> getClientes() { return this.clientes; }

    /**
     * Retorna cliente atual
     * @return clienteAtual
     */
    public Optional<Cliente> getClienteAtual() { return this.clienteAtual; }

}
