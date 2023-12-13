import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Musica implements Serializable {
    //Atributos
    protected String titulo;
    protected Artista artista;
    protected LocalDate ano;
    protected double duracao;
    protected String genero;
    protected ArrayList<Integer> rating;
    protected double ratingMedia;
    protected boolean estado;

    //Construtor que recebe titulo,ano,duracao,genero,estado
    public Musica(String titulo, Artista artista, int ano, int mes, int dia, double duracao, String genero, boolean estado) {
        this.titulo = titulo;
        this.artista = artista;
        this.ano = LocalDate.of(ano,mes,dia);
        this.duracao = duracao;
        this.genero = genero;
        this.estado = estado;
    }
    //Construtor abstrato
    public Musica() {}

    //adicionar Rating à respetiva
    public void addRating(int valor){
        rating.add(valor);
    }


    //soma de todos os ratings
    private int somaRatings(){
        int soma=0;
        for (int i=0;i<rating.size();i++){
            soma+=rating.get(i);
        }
        return soma;
    }


    //Rating final é a divisão da soma de todos os valores, dividindo pela quantidade de valores
    public void getRatingMedia(){
        this.ratingMedia=(double)(somaRatings()/rating.size());
    }

    //get título
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "titulo='" + titulo + '\'' +
                ", artista=" + artista +
                ", ano=" + ano +
                ", duracao=" + duracao +
                ", genero='" + genero + '\'' +
                ", rating=" + rating +
                ", ratingMedia=" + ratingMedia +
                ", estado=" + estado +
                '}';
    }
}
