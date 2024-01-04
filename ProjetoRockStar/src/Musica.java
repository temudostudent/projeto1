import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Musica implements Serializable {
    //Atributos
    protected int idMusica;
    protected static int ultimoId = 0;
    protected String titulo;
    protected String nomeArtista;
    protected LocalDateTime dataCriacao;
    protected String duracao;
    protected String genero;
    protected double ratingMedia;
    protected boolean estado;
    protected Map<String, Integer> rating;

    //Construtor que recebe titulo,ano,duracao,genero,estado
    public Musica(String titulo, String nomeArtista, double duracao, String genero, boolean estado) {
        this.titulo = titulo;
        this.nomeArtista = nomeArtista;
        this.dataCriacao = LocalDateTime.now();
        this.genero = genero;
        this.estado = estado;
        this.rating = new HashMap<>();
        this.idMusica = getNextId();

        double duracaoEmSegundos = duracao * 60;
        Duration d = Duration.ofSeconds((long) duracaoEmSegundos);

        long min = d.toMinutes();
        long s = d.toSeconds() - (min * 60);

        this.duracao = String.format("%02d:%02d", min, s);
    }

    public Musica() {
    }


    public static synchronized int getNextId() {
        return ++ultimoId;
    }

    //adicionar Rating à respetiva
    public void addRating(String cl ,int valor){
        rating.put(cl,valor);
    }

    //Rating final é a divisão da soma de todos os valores, dividindo pela quantidade de valores
    public String getRatingMedia() {
        if (rating.isEmpty()){
            return "NA";
        }else{
            Set<String> chaves = rating.keySet();
            double soma=0.0;
            int cont=0;
            for (String username : chaves){
                int valor = rating.get(username);
                soma+=valor;
                cont++;
            }

            this.ratingMedia=(double)soma/cont;
        }

        return String.format("%.2f", ratingMedia);
    }

    //get título
    public String getTitulo() {
        return titulo;
    }

    public void adicionarRatingMusica(String username, Integer valor){
        rating.put(username , valor);
        //Sempre que um rating é adicionado a média é novamente calculada
        getRatingMedia();
    }

    public boolean usuarioTemRating (String username){
        return rating.containsKey(username);
    }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    //get genero
    public String getGenero() {
        return genero;
    }
    public String getDuracao(){return duracao;}

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

    public LocalDateTime getOriginalDataCriacao(){
        return dataCriacao;
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


    public Integer getIdMusica() {
        return idMusica;
    }

    public Map<String, Integer> getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "idMusica=" + idMusica +
                ", titulo='" + titulo + '\'' +
                ", nomeArtista='" + nomeArtista + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", duracao='" + duracao + '\'' +
                ", genero='" + genero + '\'' +
                ", ratingMedia=" + ratingMedia +
                ", estado=" + estado +
                ", rating=" + rating +
                '}';
    }
}
