import java.util.ArrayList;

public class PlayList {
    private String nome;
   private  boolean visibilidade;

   private ArrayList<Musica> musicas;

    public PlayList(String nome, boolean visibilidade) {
        this.nome = nome;
        this.visibilidade = visibilidade;
    }

    public String getNome() {
        return nome;
    }
}
