import java.util.ArrayList;

public class PlayList {
    private String nome;
   private  boolean visibilidade;

   private ArrayList<Musica> musicas;

    public PlayList(String nome, boolean visibilidade) {
        this.nome = nome;
        this.visibilidade = visibilidade;
    }

    //Adiciona música
    public  void addMusica(Musica m){
        musicas.add(m);
    }

    //Remove música
    public  void removeMusica(Musica m){
        musicas.remove(m);
    }

    public String getNome() {
        return nome;
    }
}
