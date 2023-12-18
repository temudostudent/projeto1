import java.util.ArrayList;

public abstract class ConjuntoMusicas {

    protected String nome;
    protected ArrayList<Musica> musicas=new ArrayList<>();

    public ConjuntoMusicas(String nome) {
        this.nome = nome;
        musicas=new ArrayList<>();
    }

    public abstract void adicionarMusica(Musica m);

    public abstract void removeMusica(Musica m);

    public abstract void imprimirLista();

    public String getNome() {
        return nome;
    }
}
