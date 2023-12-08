import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private int pin;
    private Map<String,PlayList> albuns; //Atributo como map para facilitar a pesquisa de albuns
    private ArrayList<Musica> musicas=new ArrayList<>();

    //Construtor que recebe username, password, pin
    public Artista(String username, String password, int pin) {
        super(username, password);
        this.pin = pin;
    }

    //Imprimir todas as músicas do artista
    @Override
    public void verListas() {
        musicas.forEach(System.out::println);
    }

    //Cria um novo objeto Playlist e adiciona à lista do Artista, recebe o nome da playlist
    @Override
    public void criarListaMusicas(String nomeDoAlbum,boolean visibilidade) {
        PlayList novaP=new PlayList(nomeDoAlbum,visibilidade);
        albuns.put(nomeDoAlbum,novaP);
    }
    //Criar música e adiciona automaticamente à biblioteca de músicas do artista
    public void novaMusica(String titulo, int ano, double duracao, String genero, boolean estado){
        //Descobrir como colocar o próprio artista como atributo da música
        Musica novaM=new Musica(titulo,Artista this,ano,duracao,genero,estado);

        musicas.add(novaM);
    }
    //Adiciona música a Album
    public void addMusica(String nomeAlbum, String tituloMusica){
        PlayList album = albuns.get(nomeAlbum);
        if (album != null){
            for (Musica m : musicas){
                if (m.getTitulo().equalsIgnoreCase(tituloMusica)){
                    album.addMusica(m);
                }
            }
        }else{
            System.out.println("Álbum '" + nomeAlbum + "' não encontrado");
        }
    }

    //Remover música de Album


    //Escolher Album pelo nome
    public void albumDesejado(String titulo){
        for (PlayList a : albuns){
            if (a.getNome().equalsIgnoreCase(titulo)){

            }
        }
    }

}
