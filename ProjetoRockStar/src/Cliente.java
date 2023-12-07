import java.io.Serializable;
import java.util.ArrayList;

// criação classe Cliente
public class Cliente extends Utilizador implements Serializable {

    //Atributos

    private ArrayList< PlayList > playlists;

    // Construtor classe

    public Cliente(String username,String password) {
        super(username, password);
        this.playlists = playlists;
    }

    // Método para adicionar músicas a uma determinada PlayList

    public void adicionarMusica (String nomePlayList){
        for(PlayList f  : playlists){   // percorre o arrayList de playList
            if(f.getNome().equals(nomePlayList))  //verifica se o nome

        }

    }


}
