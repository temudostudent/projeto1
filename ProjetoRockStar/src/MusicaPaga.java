import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MusicaPaga extends Musica implements Serializable {

    private double preco;

   private  ArrayList <Double> historicoPreco;

    public MusicaPaga(String titulo, String nomeArtista, double duracao, String genero, boolean estado, double preco) {
        super(titulo, nomeArtista, duracao, genero, estado);
        this.dataCriacao = LocalDateTime.now();
        this.preco = preco;
        this.historicoPreco = new ArrayList<>();
    }

    public double getPreco() {return preco;}

    public void setPreco(double preco) {
        if (preco<0){
            this.preco=0;
        }else this.preco = preco;
    }






}
