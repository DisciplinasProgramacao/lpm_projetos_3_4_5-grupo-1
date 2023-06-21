## Correção Projeto 3 (branch de 02/05)

### Nota base: 18,25

### Comentários

- Os testes deveriam estar em um sorce folder diferente do código
- Evitem utilizar caracteres especiais como identificadores
- Algumas inconsitências no diagrama, como por exemplo no Cliente, loginUser()
- PlataformaStreaming deveria ser uma classe para agrupar as regras de negócio, sendo que o método main() deveria ser criado em outra classe
- Carregar dados automaticamente na criação da plataforma. Cliente não tem que escolher isso.
- Talvez seja interessante a PlataformaStreming utilizar Midias
- Como não forneceram a estrutura correta para realizar a leitura dos arquivos, foi lançado uma exceção
- Era necessário escrever apenas os arquivos de Clientes, filmes e séries 
- Existe necessidade do método audiências() no Cliente?
- Rever local de definição de genero

1. Aderência às classes do diagrama: 1,75/2 pontos
  - Diagramas

2. Requisitos de corretamente implementados: 10,5/12 pontos
  - Carga de dados					1,75/2 pontos
  - Cadastro + salvar dados			1,75/2 pontos
  - Robustez básica					0,5/1 ponto
  - Clientes							2/2 pontos
	Listas, audiência sem repet
  - Séries							1/1 ponto
	- audiência
  - Filme/Herança de mídia			1/1 ponto
  - Buscas 							2,5/3 pontos
	- nome, gênero, idioma

3. Documentação de código: 4/4 pontos

4. Implementação na aula inicial: 2/2 pontos (cliente e série testados)
