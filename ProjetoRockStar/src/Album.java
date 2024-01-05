import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Album extends ConjuntoMusicas implements Serializable {

    private String genero;
    private String nomeArtista;

    public Album(String nomeAlbum, String nomeArtista, String genero) {
        super(nomeAlbum);
        this.dataCriacao= LocalDateTime.now();
        this.nomeArtista=nomeArtista;
        this.genero = genero;
        this.musicas=new ArrayList<>();
    }
    public String getGenero() {
        return genero;
    }
}
