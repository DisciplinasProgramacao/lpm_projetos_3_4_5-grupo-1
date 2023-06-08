import java.util.Random;

public enum Genero {
    AÇÃO("Ação"), 
    ANIME("Anime"),
    AVENTURA("Aventura"),
    COMÉDIA("Comédia"),
    DOCUMENTÁRIO("Documentário"),
    DRAMA("Drama"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense");
    
    private String nome;

    Genero(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static Genero pesquisaGenero(String nome) {
        for (Genero genero : Genero.values()) {
            if (genero.nome.equalsIgnoreCase(nome)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Genero: " + nome + "não encontrado");
    }

    public static Idioma sortearGenero() {
        Idioma idioma = Idioma.values()[new Random().nextInt(Idioma.values().length)];
        return idioma;
    }
}
