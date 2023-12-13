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

    @Override
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }

    @Override
    public void removeMusica(Musica m) {
        this.musicas.remove(m);
    }

    @Override
    public void imprimirLista() {
        this.musicas.forEach(System.out::println);
    }


}
