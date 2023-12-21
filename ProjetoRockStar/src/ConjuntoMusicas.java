import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class ConjuntoMusicas {

    protected String nome;
    protected ArrayList<Musica> musicas=new ArrayList<>();
    protected LocalDateTime dataCriacao;

    public ConjuntoMusicas(String nome) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        musicas=new ArrayList<>();
    }

    public abstract void adicionarMusica(Musica m);

    public abstract void removeMusica(Musica m);

    public abstract void imprimirLista();

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return nome;
    }

    public String getDataCriacao() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dataCriacao.format(dataFormatada);
    }

}
