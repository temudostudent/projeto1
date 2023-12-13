import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MusicaPaga extends Musica implements Serializable {

    private double preco;

   private  ArrayList <Double> listaprecos = new ArrayList<>();

    public MusicaPaga(String titulo, Artista artista, int ano, int mes, int dia, double duracao, String genero, boolean estado, double preco) {
        super(titulo, artista, ano, mes, dia, duracao, genero, estado);
        this.preco = preco;
    }
}
