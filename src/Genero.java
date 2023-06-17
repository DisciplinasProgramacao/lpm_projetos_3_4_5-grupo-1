import java.util.Random;

/**
 * Enumeração de gêneros disponíveis para as mídias
 */
public enum Genero {
    /** AÇÃO */
    AÇÃO("AÇÃO"),
    /** ANIME */
    ANIME("ANIME"),
    /** AVENTURA */
    AVENTURA("AVENTURA"),
    /** COMÉDIA */
    COMÉDIA("COMÉDIA"),
    /** DOCUMENTÁRIO */
    DOCUMENTÁRIO("DOCUMENTÁRIO"),
    /** DRAMA */
    DRAMA("DRAMA"),
    /** POLICIAL */
    POLICIAL("POLICIAL"),
    /** ROMANCE */
    ROMANCE("ROMANCE"),
    /** SUSPENSE */
    SUSPENSE("SUSPENSE");

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
     * Constrói uma string com os gêneros disponíveis usando StringBuilder
     * 
     * @return string com os gêneros
     */
    public static String getGeneros() {
        StringBuilder sb = new StringBuilder();
        for (Genero genero : Genero.values())
            sb.append(" ").append(genero.getNome()).append("\n");
        return new String(sb);
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
