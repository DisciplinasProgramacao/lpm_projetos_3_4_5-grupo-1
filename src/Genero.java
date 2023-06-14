import java.util.Random;

/**
 * Enumeração de gêneros disponíveis para as mídias
 */
public enum Genero {
    /** Ação */
    AÇÃO("Ação"),
    /** Anime */
    ANIME("Anime"),
    /** Aventura */
    AVENTURA("Aventura"),
    /** Comédia */
    COMÉDIA("Comédia"),
    /** Documentário */
    DOCUMENTÁRIO("Documentário"),
    /** Drama */
    DRAMA("Drama"),
    /** Policial */
    POLICIAL("Policial"),
    /** Romance */
    ROMANCE("Romance"),
    /** Suspense */
    SUSPENSE("Suspense");

    /** Nome do gênero */
    private String nome;

    /**
     * Construtor
     * 
     * @param nome do gênero
     */
    Genero(String nome) {
        this.nome = nome;
    }

    /**
     * Permite obter o nome do gênero
     * 
     * @return nome do gênero
     */
    public String getNome() {
        return nome;
    }

    /**
     * Pesquisa um gênero pelo nome
     * 
     * @param nome do gênero
     * @return gênero
     */
    public static Genero pesquisaGenero(String nome) {
        return Genero.valueOf(nome.toUpperCase()); // caram q fez, aprende igor
    }

    /**
     * Usado ao criar midias, sorteia um gênero
     * 
     * @return gênero aleatório
     */
    public static Genero sortearGenero() {
        return Genero.values()[new Random().nextInt(Genero.values().length)];
    }
}
