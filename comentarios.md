# Comentários - Projeto 4 (30/05)

## Nota base: 15,75

### Comentários

- Backlog preenchido
- Instruções de uso preenchido
- Video de apresentação não realizado
- Documentação de código realizada, não existe necessidade em documentar nome da variavel
- A carga de dados pode ser executada ao abrir a aplicação
- Diagrama desatualizado em IMidia
- A aplicação permite realizar a avaliação mais de uma vez
- Não está calculando média de avaliações da maneira correta
- Rever necessidade de getQntEp() em Filmes
- Rever necessidade de IMidia, pois a preferencia pela interface está gerando código duplicado para registrarAudiencia() e registrarAvaliacao(), o mesmo ocorre com os Clientes e filtragens
- Rever necessidade de  private HashMap<String, Integer> nomes  e ele também está ocasionando em buscarMidia() um aninhamento de operações
- Uma possível solução ao implementar a classe Avaliacao seria definir uma interface de Avaliadores para diferenciar os Clientes
- Utilizem enumeradores quando necessário

----
	
- Aderência às classes do diagrama: 1,75/2 pontos
- Requisitos de corretamente implementados: 10/14 pontos
    - só pode avaliar o que tiver visto		1/2 pontos
    - avaliar, calcular e exibir media 		1,5/2 pontos
    - cliente não pode avaliar 2x			0/3 pontos
    - especialistas podem comentar			4/4 pontos
    - verificação de especialistas			3,5/3 pontos
	
- Documentação de código: 2/2 pontos

- Implementação na aula inicial: 2/2 pontos (02/05)
    - arquivos JavaDoc  
    - diagrama atualizado 
    - backlog de pendências

----