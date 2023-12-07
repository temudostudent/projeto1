import java.io.Serializable;
import java.util.ArrayList;

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private int pin;
    private ArrayList<PlayList> albuns;
    private ArrayList<Musica> musicas;

    //Construtor que recebe username, password, pin
    public Artista(String username, String password, int pin) {
        super(username, password);
        this.pin = pin;
    }

    //Imprimir todas as músicas do artista
    @Override
    public void verListas() {
        musicas.forEach(System.out::println);
    }

    //Cria um novo objeto Playlist e adiciona à lista do Artista, recebe o nome da playlist
    @Override
    public void criarListaMusicas(String nomeDoAlbum,boolean visibilidade) {
        PlayList nova=new PlayList(nomeDoAlbum,visibilidade);
        albuns.add(nova);
    }
    //Adiciona música
    public void addMusica(String titulo, int ano, double duracao, String genero, boolean estado){


    }
}
