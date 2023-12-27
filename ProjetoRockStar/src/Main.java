import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        try {
            JanelaInicial run = new JanelaInicial();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //new InterfaceArtista();

        //Cliente c=new Cliente();


        //GestaoApp gb=new GestaoApp();
       //new InterfaceCliente(c,gb);
    }
}
