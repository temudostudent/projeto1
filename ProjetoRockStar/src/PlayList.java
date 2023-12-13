import java.util.ArrayList;
import java.util.Random;

public class PlayList extends ConjuntoMusicas{

   private boolean visibilidade;

    public PlayList(String nome, boolean visibilidade) {
        super(nome);
        this.visibilidade = visibilidade;
        super.musicas=new ArrayList<>();
    }

    //Adiciona música a Playlist
    @Override
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }

    //Remove música da Playlist
    @Override
    public void removeMusica(Musica m) {
        this.musicas.remove(m);
    }

    //Imprime todos os items da Playlist
    @Override
    public void imprimirLista() {
        this.musicas.forEach(System.out::println);
    }

    public void percorrerPlayList(int tamanho){
        int cont = 0;
        ArrayList nova = new ArrayList<>();
        Random random = new Random();
        for(Musica m : musicas) {
        }
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

    //Mostra a visibilidade da Playlist
    public boolean isVisibilidade() {
        return visibilidade;
    }

    //Define a visibilidade da Playlist
    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    //Get nome da Playlist
    public String getNome() {
        return nome;
    }
}
