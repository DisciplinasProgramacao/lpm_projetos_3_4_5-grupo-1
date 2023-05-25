import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Clase que representa uma plataforma de streaming.
 */
public class PlataformaStreaming {

    /** Nome da plataforma de streaming */
    private String nome;

    /** Series da plataforma */
    private HashMap<Integer, IMidia> midias;

    /** Clientes da plataforma */
    private HashMap<String, ICliente> clientes;

    /** Cliente logado atualmente na plataforma */
    private Optional<ICliente> clienteAtual;

    /**
     * Construtor da classe PlataformaStreaming.
     * 
     * @param nome Nome da plataforma de streaming.
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.midias = new HashMap<Integer, IMidia>();
        this.clientes = new HashMap<String, ICliente>();
        this.logOff();
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
    public void adicionarMidia(IMidia midias) {
        this.midias.put(midias.getID(), midias);
    }

    /**
     * Adiciona um cliente na plataforma de streaming.
     * 
     * @param cliente novo cliente a ser adicionado.
     */
    public void adicionarCliente(ICliente cliente) {
        this.clientes.put(cliente.getLogin(), cliente);
    }

    /**
     * Adiciona uma midia a lista de midias ja assistidas pelo cliente atual e
     * incrementa a audiencia da midia.
     * 
     * @param completado se o cliente assistiu a midia por completo.
     * @param midia      a ter a audiencia incrementada.
     */ // @formatter:off
    public void registrarAudiencia(boolean completado, IMidia midia, boolean avaliacao) {
        if (midia == null) { // Se a midia nao existir, nao e possivel registrar audiencia
            System.out.println("IMidia nao encontrada"); return;
        }

        if (this.clienteAtual.isEmpty()) { // Se nao houver cliente logado, nao e possivel registrar audiencia
            System.out.println("Nenhum cliente logado, nao e possivel registrar audiencia."); return;
        }

        if (!completado) { // Atualiza o cliente caso ele se torne especialista
            this.clienteAtual.get().adicionarNaLista(midia); return;
        }

        // Registra a audiencia da midia. Se o cliente se tornar especialista, atualiza a referencia ao cliente @formatter:on
        clienteAtual = Optional.of(this.clienteAtual.get().registrarAudiencia(midia,
                avaliacao ? App.lerInt(" De uma nota de 1 a 5 a midia, digite 0 para ignorar") : 0,
                LocalDate.now() //
        ));
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
        return this.midias.values().stream()
                .filter(midia -> midia.getGenero().equals(genero))
                .map(Object::toString);

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
        return this.midias.values().stream()
                .filter(midia -> midia.getIdioma().equals(idioma))
                .map(Object::toString);
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
        return this.midias.values().stream()
                .filter(midia -> midia.getQntEp() == quantidadeEpisodios)
                .map(Object::toString);
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
        return this.midias.values().stream()
                .filter(midia -> midia.getDuracao() == duracao)
                .map(Object::toString);
    }

    /**
     * Busca uma midia na plataforma de streaming pelo nome.
     * 
     * @param idMidia id da midia a ser buscada.
     * @return IMidia com o nome passado como parâmetro, NULL caso nao exista.
     */
    public IMidia buscarMidia(int idMidia) {
        return this.midias.get(idMidia);
    }

    /**
     * Retorna uma string que representa a plataforma de streaming com o nome e
     * número de midias e clientes cadastrados.
     * 
     * @return String que representa a plataforma de streaming.
     */
    @Override // @formatter:off
    public String toString() {
        return " Ha " + this.midias.size() + " midias cadastradas na plataforma " + this.nome + " e " + this.clientes.size() + " clientes cadastrados.";
    }

    /** Retorna as midias
     * @return midias*/
    public HashMap<Integer, IMidia> getMidia() { return this.midias; }

    /** Retorna os clientes
     * @return clientes*/
    public HashMap<String, ICliente> getClientes() { return this.clientes; }

}