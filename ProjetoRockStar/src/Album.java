import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Album extends ConjuntoMusicas implements Serializable {
    /**
     * @author César Temudo
     * @author Vânia Mendes
     * @version 1.0
     */
    private String genero;
    private String nomeArtista;

    /**
     * Construtor de um álbum de um género especificado pelo artista.
     * @param nomeAlbum
     * @param nomeArtista
     * @param genero
     */
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
