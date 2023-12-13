import java.util.ArrayList;

public class Album extends ConjuntoMusicas{

    private String genero;
    private Artista artista;

    public Album(String nome, Artista artista, String genero) {
        super(nome);
        this.artista=artista;
        this.genero = genero;
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


}
