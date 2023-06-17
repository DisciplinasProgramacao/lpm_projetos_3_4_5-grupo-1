import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class FabricaCliente implements Fabrica {

    @Override
    public Object criar(String[] info) {
        return new Cliente(
                info[0], // Nome
                info[1], // Login
                info[2] // Senha
        );
    }

    @Override
    public void escrever(Stream<Object> obj) throws IOException {
        FileWriter clientes = new FileWriter(Cliente.ARQUIVO);

        obj.forEach(cliente -> {
            try {
                clientes.write(cliente + "\n");
            } catch (IOException e) {
                System.out.println("Erro ao escrever arquivo de clientes durante escrita" + e.getMessage());
            }
        });

        clientes.close();
    }

}
