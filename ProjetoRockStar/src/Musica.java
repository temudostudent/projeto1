import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Musica implements Serializable {
    //Atributos
    protected String titulo;
    protected String nomeArtista;
    protected LocalDateTime dataCriacao;
    protected double duracao;
    protected String genero;
    protected ArrayList<Integer> rating;
    protected double ratingMedia;
    protected boolean estado;

    //Construtor que recebe titulo,ano,duracao,genero,estado
    public Musica(String titulo, String nomeArtista, double duracao, String genero, boolean estado) {
        this.titulo = titulo;
        this.nomeArtista = nomeArtista;
        this.dataCriacao = LocalDateTime.now();
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

    public void setTitulo(String titulo) { this.titulo = titulo; }

    //get genero
    public String getGenero() {
        return genero;
    }
    public Double getDuracao(){return duracao;}

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public boolean getEstado(){ return estado;}


    public String getNomeArtista() {
        return nomeArtista;
    }

    public String getDataCriacao() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dataCriacao.format(dataFormatada);
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "titulo='" + titulo + '\'' +
                ", artista=" + nomeArtista +
                ", ano=" + dataCriacao +
                ", duracao=" + duracao +
                ", genero='" + genero + '\'' +
                ", rating=" + rating +
                ", ratingMedia=" + ratingMedia +
                ", estado=" + estado +
                '}';
    }
}
