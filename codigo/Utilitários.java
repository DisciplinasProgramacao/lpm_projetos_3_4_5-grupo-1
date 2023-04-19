/**
 * Classe de utilitários.
 */
public class Utilitários {

    /**
     * Método que lê um inteiro do console.
     * 
     * @param mensagem mensagem a ser exibida ao usuário.
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
                " 3 - Cadastrar Cliente\n" +
                " 4 - Cadastrar Série\n" +
                " 5 - Adicionar Audiência já vista\n" + /* @formatter:off
                " 6 - Filtrar por Gênero\n" +
                " 7 - Filtrar por Idioma\n" +
                " 8 - Filtrar por Quantidade de Episódios\n" + @formatter:on */
                " 9 - Sair\n\n" +
                " Digite uma opção: ");

    }
    
}
