import java.io.Serializable;
import java.util.*;

/**
 * @author cesartemudo
 * @version 1.0
 */

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private int pin;
    private Map<String,PlayList> albuns=new Map<String, PlayList>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public PlayList get(Object key) {
            return null;
        }

        @Override
        public PlayList put(String key, PlayList value) {
            return null;
        }

        @Override
        public PlayList remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends String, ? extends PlayList> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<String> keySet() {
            return null;
        }

        @Override
        public Collection<PlayList> values() {
            return null;
        }

        @Override
        public Set<Entry<String, PlayList>> entrySet() {
            return null;
        }
    }; //Atributo como map para facilitar a pesquisa de albuns
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
    public void novaMusica(String titulo, int ano, int mes, int dia, double duracao, String genero, boolean estado){
        //Descobrir como colocar o próprio artista como atributo da música
        Musica novaM=new Musica(titulo,this,ano,mes,dia,duracao,genero,estado);

        musicas.add(novaM);
    }
    //Adiciona música a Album
    //Pesquisa no map se o álbum existe, se existir vai procurar a música na biblioteca do artista e adiciona a esse álbum
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
    public void removeMusica(String nomeAlbum, String tituloMusica){
        PlayList album = albuns.get(nomeAlbum);
        if (album != null){
            for (Musica m : musicas){
                if (m.getTitulo().equalsIgnoreCase(tituloMusica)){
                    album.removeMusica(m);
                }
            }
        }else{
            System.out.println("Álbum '" + nomeAlbum + "' não encontrado");
        }
    }

}
