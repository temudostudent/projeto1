import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        try {
            JanelaInicial run = new JanelaInicial();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
