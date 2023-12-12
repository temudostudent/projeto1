import java.util.ArrayList;

public class PlayList {
    private String nome;
   private  boolean visibilidade;

   private ArrayList<Musica> musicas;

    public PlayList(String nome, boolean visibilidade) {
        this.nome = nome;
        this.visibilidade = visibilidade;
        this.musicas = new ArrayList<>();
    }
    public PlayList(){};

    public void adicionarMusica(Musica m){
        musicas.add(m);
    }
    public Musica encontrarMusicaGenero( String genero){
        Musica g = null;
        for(Musica m : musicas){
            if(m.getGenero().equals(genero)){
                g = (Musica) m;
            }
        }
        return g;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "nome='" + nome + '\'' +
                ", visibilidade=" + visibilidade +
                ", musicas=" + musicas +
                '}';
    }
}
