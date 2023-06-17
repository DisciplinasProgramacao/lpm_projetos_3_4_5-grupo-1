import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class FabricaSerie implements Fabrica {

    @Override
    public Object criar(String[] info) {
        return new Serie(
                Integer.parseInt(info[0]), // IdSerie
                info[1], // Nome
                LocalDate.parse(info[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"))// DataDeLançamento
        );
    }

    @Override
    public void escrever(Stream<Object> obj) throws IOException {
        FileWriter series = new FileWriter(Serie.ARQUIVO);

        obj.forEach(serie -> {
            try {
                series.write(((Serie) serie).toFile() + "\n");
            } catch (IOException e) {
                System.out.println("Erro ao escrever arquivo de series durante escrita" + e.getMessage());
            }
        });

        series.close();
    }

}
