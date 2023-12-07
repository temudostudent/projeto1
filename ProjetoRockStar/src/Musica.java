import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

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
    public Musica(String titulo, Artista artista, LocalDate ano, double duracao, String genero, boolean estado) {
        this.titulo = titulo;
        this.artista = artista;
        this.ano = ano;
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
    public void ratingMedia(){
        this.ratingMedia=(double)(somaRatings()/rating.size());
    }

}
