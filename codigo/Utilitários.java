/**
 * Classe de utilitários.
 */
public class Utilitários {

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
     * Método que lê uma opção do console e printa o menu para o usuário.
     * 
     * @return opção lida do console.
     */
    public static int menu() {
        return lerInt("\n 1 - Ler Arquivos\n" +
                " 2 - Salvar Arquivos\n" +
                " 3 - Filtrar por Gênero\n" +
                " 4 - Filtrar por Idioma\n" +
                " 5 - Filtrar por Quantidade de Episódios\n" +
                " 6 - Printar info do sistema\n" +
                " 7 - Login\n" +
                " 8 - Logout\n" +
                " 9 - Buscar Série\n" +
                " 10 - Sair\n\n" +
                " Digite uma opção: ");

    }

}
