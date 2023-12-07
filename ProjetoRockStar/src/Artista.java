import java.io.Serializable;
import java.util.ArrayList;

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private int pin;
    private ArrayList<Album> albuns;
    private ArrayList<Musica> musicas;

    //Construtor que recebe username, password, pin

    public Artista(String username, String password, int pin) {
        super(username, password);
        this.pin = pin;
    }
}
