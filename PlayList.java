import java.util.ArrayList;

public class PlayList {
    private String nome;
   private  Boolean visibilidade;

   private ArrayList<Musica> musicas;

    public PlayList(String nome, Boolean visibilidade) {
        this.nome = nome;
        this.visibilidade = visibilidade;
    }

}
