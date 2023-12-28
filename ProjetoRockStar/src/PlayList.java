import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class PlayList extends ConjuntoMusicas implements Serializable {
   private  boolean visibilidade;


    public PlayList(String nome, boolean visibilidade) {
        super(nome);
        this.visibilidade = visibilidade;
    }
    public String toString() {
        if (visibilidade) {
            return nome + ",   PÚBLICA";
        } else {
            return nome + ",   PRIVADA";
        }
    }

    //Adiciona música
    @Override
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }

    //Remove música
    @Override
    public  void removeMusica(Musica m){
        this.musicas.remove(m);
    }


    public String getNome() {
        return this.nome;
    }

    public int getNumMusicas(){return this.musicas.size();}

    //Mostra a visibilidade
    public boolean isVisibilidade() {
        return this.visibilidade;
    }

    //Set visibilidade
    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }
}
