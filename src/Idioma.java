import java.util.Random;

/**
 * Enumeração de idiomas disponíveis para as mídias
 */
public enum Idioma {
    /** Português */
    PORTUGUÊS("Português"),
    /** Inglês */
    INGLÊS("Inglês"),
    /** Espanhol */
    ESPANHOL("Espanhol"),
    /** Francês */
    FRANCÊS("Francês"),
    /** Alemão */
    ALEMÃO("Alemão"),
    /** Italiano */
    ITALIANO("Italiano");

    /** Nome do idioma */
    private String nome;

    /**
     * Construtor
     * 
     * @param nome do idioma
     */
    Idioma(String nome) {
        this.nome = nome;
    }

    /**
     * Permite obter o nome do idioma
     * 
     * @return nome do idioma
     */
    public String getNome() {
        return nome;
    }

    public static String getIdiomas() {
        StringBuilder sb = new StringBuilder();
        for (Idioma idioma : Idioma.values())
            sb.append(" ").append(idioma.getNome()).append("\n");
        return new String(sb);
    }

    /**
     * Pesquisa um idioma pelo nome
     * 
     * @param nome do idioma
     * @return idioma
     */
    public static Idioma pesquisaIdioma(String nome) {
        return Idioma.valueOf(nome.toUpperCase());
    }

    /**
     * Usado ao criar midias, sorteia um idioma
     * 
     * @return idioma aleatório
     */
    public static Idioma sortearIdioma() {
        return Idioma.values()[new Random().nextInt(Idioma.values().length)];
    }
}
