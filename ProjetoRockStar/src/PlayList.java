import java.util.ArrayList;
import java.util.Random;

public class PlayList extends ConjuntoMusicas{
   private  boolean visibilidade;

    public PlayList(String nome, boolean visibilidade) {
        super(nome);
        this.visibilidade = visibilidade;
        super.musicas = new ArrayList<>();
    }

    //Adiciona música
    @Override
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }

    //Remove música
    public  void removeMusica(Musica m){
        this.musicas.remove(m);
    }

    @Override
    public void imprimirLista() {
        musicas.forEach(System.out::println);
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

    public String getNome() {
        return this.nome;
    }

    //Mostra a visibilidade
    public boolean isVisibilidade() {
        return this.visibilidade;
    }

    //Set visibilidade
    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }
}
