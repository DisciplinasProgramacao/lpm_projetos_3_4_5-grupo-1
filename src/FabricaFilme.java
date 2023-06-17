import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class FabricaFilme implements Fabrica {

    @Override
    public Object criar(String info[]) {
        return new Filme(Integer.parseInt(info[0]), // IdSerie
                info[1], // Nome
                LocalDate.parse(info[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), // DataDeLançamento
                Integer.parseInt(info[3]) // duração
        );
    }

    @Override
    public void escrever(Stream<Object> obj) throws IOException {
        FileWriter filmes = new FileWriter(Filme.ARQUIVO);

        obj.forEach(filme -> {
            try {
                filmes.write(((Filme) filme).toFile() + "\n");
            } catch (IOException e) {
                System.out.println("Erro ao escrever arquivo de filmes durante escrita" + e.getMessage());
            }
        });

        filmes.close();
    }

}
