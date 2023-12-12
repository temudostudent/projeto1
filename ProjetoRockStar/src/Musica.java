import java.io.Serializable;
import java.time.LocalDate;

public class Musica implements Serializable {
    //Atributos
    protected String titulo;
    protected Artista artista;
    protected LocalDate ano;
    protected double duracao;
    protected String genero;
    protected double rating;
    protected boolean estado;

    //Construtor que recebe titulo,ano,duracao,genero,estado
    public Musica(String titulo, LocalDate ano, double duracao, String genero, boolean estado) {
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
        this.genero = genero;
        this.estado = estado;
    }
}
