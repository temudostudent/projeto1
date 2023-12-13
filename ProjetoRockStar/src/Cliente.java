import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// criação classe Cliente
public class Cliente extends Utilizador implements Serializable {

    //Atributos

    private ArrayList<PlayList> playlists;
    private ArrayList<Compra> compras;

    // Construtor classe

    public Cliente(String username,String password) {
        super(username, password);
        this.playlists = playlists;
    }

    @Override
    public void verListas() {
        for(PlayList f : playlists){
            System.out.println(f);
        }

    }

    @Override
    public void criarListaMusicas(String nomeDaLista, boolean visibilidade) {
        PlayList nova = new PlayList(nomeDaLista, visibilidade);
        playlists.add(nova);
    }

    // Método para adicionar músicas a uma determinada PlayList
    public void adicionarMusica(String nomePlayList, Musica musica) {
        for (PlayList f : playlists) {                                        // percorre o arrayList de playList
            if (f.getNome().equals(nomePlayList)) {                          //pesquisa o nome da playList
                f.adicionarMusica(musica);                                   // adiciona a musica à lista
            }
        }
    }

    // criar uma playList indicando o género e tamanho
    public void criarPlayListGenero(String genero, int tamanho) {
        String nome;
        int cont = 0;
        boolean visibilidade;
        PlayList nova = new PlayList();                                     // cria uma nova playList
        for(PlayList f : playlists ){                                        // percorre todas as playlists
            nova.adicionarMusica( f.encontrarMusicaGenero(genero));          // encontra musica pelo genero e adiciona-a a uma nova playList
        }

    }

    // remover um playList dando o atributo nome
    public void removerPlayList(String nome) {
        for (PlayList play : playlists) {
            if (play.getNome().equals(nome)) {
                playlists.remove(play);
            }
        }
    }

    // alterar saldo do cliente informando a quantidade
    public void alterarSaldo(double quantia) {
        saldo *= quantia;
    }
}
