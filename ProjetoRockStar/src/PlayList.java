import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class PlayList extends ConjuntoMusicas implements Serializable {
   private  boolean visibilidade;

    public PlayList(String nome, boolean visibilidade) {
        super(nome);
        this.visibilidade = visibilidade;
        super.musicas = new ArrayList<>();
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

    @Override
    public void imprimirLista() {
        musicas.forEach(System.out::println);
    }


    public String getNome() {
        return this.nome;
    }

    //Mostra a visibilidade
    public boolean isVisibilidade() {
        return this.visibilidade;
    }

    //Set visibilidade
    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }
}
