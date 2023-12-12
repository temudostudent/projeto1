import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MusicaPaga extends Musica implements Serializable {

    private double preco;

   private  ArrayList <Double> listaprecos = new ArrayList<Double>();

    public MusicaPaga(String titulo, LocalDate ano, double duracao, String genero, boolean estado, double preco) {
        super(titulo, ano, duracao, genero, estado);
        this.preco = preco;
    }
}
