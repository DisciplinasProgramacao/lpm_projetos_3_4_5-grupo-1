# Padrões de Projeto

A aplicação utiliza de três padrões de projeto, eles se encontram a baixo:

- SINGLETON - Este padrão de projeto garante que a instância da plataforma seja única, mantendo apenas uma instância da PlataformaStreaming, utilizamos, na classe PlataformaStreaming.java, uma varíavel static chamada 'instance' do próprio tipo da Plataforma, que guarda sua instância criada, e um método chamado getInstance() que serve para anular a criação de uma nova PlataformaStreaming caso a variavel 'instace' não esteja nula.

- FÁBRICA - Este padrão de projeto gera uma interface que possui o intuito de ler e escrever os arquivos .csv, a aplicação utiliza de várias classes Fábricas, como a propria Fabrica.java, sendo esta a interface, e as classes que a implementam, FabricaCliente.java, FabricaFilme.java e FabricaSerie.java. A classe LeitorEscritor.java fica responsável pela leitura e escrita dos arquivos .csv e criação das Fábricas.

- STATE - Este padrão de projeto foi utilizado na classe de Avaliações e, consequentemente, suas filhas. Na aplicação, ele é utilizado para definir o tipo de avaliação que o cliente pode fazer, alternando entre Avaliação Comum e Avaliação Especializada. Utilizamos dos métodos goNext() e goPrev() para transitar entre esses "estados", garantindo que o cliente pode tanto ganhar, quanto perder o acesso à função de avaliar especializadamente.

## Alunos integrantes da equipe

- Gabriel Dolabela Marques
- Henrique Carvalho Almeida
- Igor Pinheiro dos Santos
- João Pedro de Oliveira Pauletti
- Lucas Monteiro Lima

## Professores responsáveis

- Cleiton Silva Tavares
