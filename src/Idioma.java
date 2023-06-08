import java.util.Random;

public enum Idioma {
    PORTUGUÊS("Português"), 
    INGLÊS("Inglês"), 
    ESPANHOL("Espanhol"), 
    FRANCÊS("Francês"), 
    ALEMÃO("Alemão"), 
    ITALIANO("Italiano");

    private String nome;

    Idioma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static Idioma pesquisaIdioma(String nome) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.nome.equalsIgnoreCase(nome)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma: " + nome + "não encontrado");
    }

    public static Genero sortearGenero() {
        Genero genero = Genero.values()[new Random().nextInt(Genero.values().length)];
        return genero;
    }
}
