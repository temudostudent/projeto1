import java.io.Serializable;
import java.util.ArrayList;

public class Album extends ConjuntoMusicas implements Serializable {

    private String genero;
    private String nomeArtista;

    public Album(String nome, String nomeArtista, String genero) {
        super(nome);
        this.nomeArtista=nomeArtista;
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

    //Procura música no álbum pelo título da música
    public Musica encontraMusica(String tituloMusica){
        Musica musica=new Musica();
        for (Musica mVariavel : this.musicas){
            if (mVariavel.getTitulo().equalsIgnoreCase(tituloMusica)){
                return musica=mVariavel;
            }else System.out.println("Música '" + tituloMusica + "' não encontrado");
        }
    return musica;}

    @Override
    public String getNome() {
        return super.getNome();
    }

    public String getGenero() {
        return genero;
    }

    public String getNomeArtista() { return nomeArtista;}
}
