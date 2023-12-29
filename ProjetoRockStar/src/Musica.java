import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Musica implements Serializable {
    //Atributos
    protected String titulo;
    protected String nomeArtista;
    protected LocalDateTime dataCriacao;
    protected double duracao;
    protected String genero;
    //protected ArrayList<Integer> rating=new ArrayList<>();
    protected double ratingMedia;
    protected boolean estado;
    protected Map<Cliente, Integer> rating = new HashMap<>();

    //Construtor que recebe titulo,ano,duracao,genero,estado
    public Musica(String titulo, String nomeArtista, double duracao, String genero, boolean estado) {
        this.titulo = titulo;
        this.nomeArtista = nomeArtista;
        this.dataCriacao = LocalDateTime.now();
        this.duracao = duracao;
        this.genero = genero;
        this.estado = estado;
        this.ratingMedia = ratingMedia;
        this.rating=rating;
    }

    public Musica() {
    }

    //adicionar Rating à respetiva
    public void addRating(Cliente cl ,int valor){
        rating.put(cl,valor);
    }

    //Rating final é a divisão da soma de todos os valores, dividindo pela quantidade de valores
    public String getRatingMedia() {
        if (rating.size()==0){
            return "NA";
        }else{
            Set<Cliente> chaves = rating.keySet();
            int soma=0;
            int cont=0;
            for (Cliente c : chaves){
                int valor = rating.get(c);
                soma+=valor;
                cont++;
            }

            this.ratingMedia=(double)(soma/cont);
        }

        return String.valueOf(ratingMedia);
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
    public boolean getEstado(){
        return estado;}

    public String tipoEstado(){
        if(getEstado() == true){
            return "ATIVA";
        }else{
            return "INATIVA";
        }
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public String getDataCriacao() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dataCriacao.format(dataFormatada);
    }

    @Override
    public boolean equals(Object m){
        if (this == m) {
            return true;
        }
        if (m == null || getClass() != m.getClass()) {
            return false;
        }
        Musica objeto = (Musica) m;
        return true;
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
