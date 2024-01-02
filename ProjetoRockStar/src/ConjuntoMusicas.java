import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class ConjuntoMusicas implements Serializable {

    protected String nome;
    protected ArrayList<Musica> musicas;
    protected LocalDateTime dataCriacao;

    public ConjuntoMusicas(String nome) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        musicas=new ArrayList<>();
    }

    public abstract void adicionarMusica(Musica m);

    public abstract void removeMusica(Musica m);


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

    @Override
    public boolean equals(Object p){
        if (this == p) {
            return true;
        }
        if (p == null || getClass() != p.getClass()) {
            return false;
        }
        ConjuntoMusicas objeto = (ConjuntoMusicas) p;
        return true;
    }

}
