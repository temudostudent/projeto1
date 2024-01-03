import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Album extends ConjuntoMusicas implements Serializable {
    private Integer idAlbum;
    private Integer ultimoID = 0;

    private String genero;
    private String nomeArtista;

    public Album(String nomeAlbum, String nomeArtista, String genero) {
        super(nomeAlbum);
        this.dataCriacao= LocalDateTime.now();
        this.nomeArtista=nomeArtista;
        this.genero = genero;
        this.musicas=new ArrayList<>();
        this.idAlbum = ultimoID++;
    }


    //Adiciona música a Playlist
    @Override
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }


    //Procura música no álbum pelo título da música
    public Musica encontraMusica(String tituloMusica){
        Musica musica=null;
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

    public Integer getIdAlbum() {
        return idAlbum;
    }
}
