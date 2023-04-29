/**
 * Classe de utilitários.
 */
final public class Utilitários {

    /**
     * Construtor vazio.
     */
    private Utilitários() {
        throw new Error("Classe não instanciável");
    }

    /**
     * Método que lê um inteiro do console.
     * 
     * @param mensagem a ser exibida ao usuário.
     * @return inteiro lido do console.
     */
    public static int lerInt(String mensagem) {
        try {
            return Integer.parseInt(System.console().readLine(mensagem));
        } catch (NumberFormatException e) {
            return lerInt(" ERRO: Valor inválido. Digite um número inteiro: ");
        }
    }

    /**
     * Printa um menu de usuário no console.
     * 
     * @return opção lida do console.
     */
    public static int menu() {
        return lerInt("\n 1 - Ler Arquivos\n" +
                " 2 - Salvar Arquivos\n" +
                " 3 - Filtrar por Gênero\n" +
                " 4 - Filtrar por Idioma\n" +
                " 5 - Filtrar por Quantidade de Episódios\n" +
                " 6 - Filtrar filmes por duração\n" +
                " 7 - Login\n" +
                " 8 - Logout\n" +
                " 9 - Adicionar Mídia/Filme/Audiência\n" +
                " 10 - Buscar Mídia\n" +
                " 11 - Sair\n\n" +
                " Digite uma opção: " //
        );

    }

    /**
     * Printa um menu de usuário no console.
     * 
     * @return opção lida do console.
     */
    public static int menuAdd() {
        return lerInt("\n 1 - Adicionar Série\n" +
                " 2 - Adicionar Filme\n" +
                " 3 - Adicionar Cliente\n" +
                " 4 - Adicionar Audiência\n" +
                " 5 - Voltar\n\n" +
                " Digite uma opção: " //
        );
    }

}
