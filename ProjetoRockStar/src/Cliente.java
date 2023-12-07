import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// criação Classe Cliente
public class Cliente extends Utilizador implements Serializable {

    //Atributos

    private ArrayList< PlayList > playlists;

    // Construtor classe Cliente

    public Cliente(String username,String password) {
        super(username, password);
        this.playlists = playlists;
    }

    // Método para adicionar músicas a uma determinada PlayList
    public void adicionarMusica (String nomePlayList, Musica musica){
        for(PlayList f  : playlists){   // percorre o arrayList de playList
            if(f.getNome().equals(nomePlayList)){//pesquisa o nome da playList
                f.add(musica); // adiciona a musica à lista
            }
        }
    }

    // criar uma playList pelo género
    public void criarPlayListGenero (String genero, int tamanho){
        String nome;
        boolean visibilidade;
        PlayList nova = new PlayList<Musica>(tamanho);   // cria uma nova playList
        for(Musica musica : listaGlobalmusicas){
            if(musica.getGenero.equals(genero)){
                nova.add(musica);                       // adiciona todas as musicas do genero selecionado
            }
        }
        Random aleatorio = new Random();


    }
    // remover um playList dando o atributo nome
    public void removerPlayList(String nome){
        for(PlayList play : playlists){
            if(play.getNome().equals(nome)){
                playlists.remove(play);
            }
        }
    }
    // carregar saldo do cliente informando a quantidade
    public void alterarSaldo(double quantia){
        saldo *= quantia;
    }
}
