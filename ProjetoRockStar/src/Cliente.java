import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author César Temudo
 * @autor Vânia Mendes
 * @version 1.0
 */

public class Cliente extends Utilizador implements Serializable {

    //Atributos
    private ArrayList<PlayList> playlists;
    private ArrayList<Compra> historicoCompras;
    private ArrayList<MusicaPaga> musicasCompradas;
    protected Compra compra;

    /**
     * Construtor da classe Cliente, que recebe um username e password, cria uma lista de playlists, de historico de
     * compras e de musicas compradas e atribui o saldo nulo ao cliente.
     * @param username
     * @param password
     */
    public Cliente(String username,String password) {
        super(username, password);
        this.playlists = new ArrayList<>();
        this.historicoCompras = new ArrayList<>();
        super.saldo = 0;
        this.musicasCompradas=new ArrayList<>();
    }

    /**
     * Cria uma nova playlist associada ao cliente.
     * @param nomeDaLista: nome da nova playlist a ser criada.
     * @param visibilidade que pode ser true ou false, caso a playlist criada seja pública ou privada.
     * @return instancia da nova playlist criada.
     */
    public PlayList criarPlaylist(String nomeDaLista, boolean visibilidade) {
        PlayList nova = new PlayList(nomeDaLista, visibilidade, this.username);
        return nova;
    }

    /**
     * Verifica se o nome de uma playlist já existe entre as playlists do cliente.
     * @param nome da playlist a ser verificada.
     * @return true se o nome estiver disponível e false se já existir.
     */
    public boolean verificarNomePlaylist(String nome) {
        boolean nomeOK = true;
        for (PlayList play : playlists) {
            if (play.getNome().equals(nome)) {
                nomeOK = false;
            } else {
                nomeOK = true;
            }
        }
        return nomeOK;
    }


    /**
     * Adiciona uma musica a uma playlist especifica do cliente
     * @param p: playlist à qual a música será adicionada.
     * @param m: música a ser adicionada à playlist.
     */
    public void adicionarMusica(PlayList p, Musica m) {
        p.adicionarMusica(m);
    }


    /**
     * Pesquisa uma playlist do cliente com base no titulo fornecido.
     * @param titulo da playlist a ser pesquisado.
     * @return a instância da playlist encontrada com o titulo fornecido, ou null caso não encontre.
     */
    public PlayList pesquisaPlaylistTitulo(String titulo) {
        PlayList objeto = null;
        for (PlayList p : playlists) {
            if (p.getNome().equals(titulo)) {
                objeto = p;
            }
        }
        return objeto;
    }


    /**
     * Cria uma lista contendo músicas gratuitas e compradas pelo cliente.
     * @param listaMusicasGratuitas a serem incluidas na lista de musicas do mesmo género.
     * @return lista contendo musicas gratuitas e compradas pleo cliente do mesmo género.
     */
    public ArrayList listaMusicaGenero(ArrayList<Musica> listaMusicasGratuitas, String genero) {
        ArrayList<Musica> musicasGenero = listaMusicasGratuitas;
        for (Musica musica : musicasCompradas) {
            if (musica.getGenero().equalsIgnoreCase(genero)){
                musicasGenero.add(musica);
            }
        }
        return musicasGenero;
    }


    /**
     * Cria uma nova playlist com musicas de um tipo de género.
     * @param titulo da nova playlist.
     * @param tamanho desejado para a nova playlist.
     * @param listaMusicaGenero: lista de musicas do género a ser considerado para a criação da nova playlist.
     * @return a instância da nova playlist criada com musicas do género pretendido.
     */
    public PlayList criarPlayListGenero(String titulo, int tamanho, ArrayList listaMusicaGenero) {
        PlayList nova = new PlayList(titulo, true, this.username);

        //Baralhar a lista de músicas do género
        Collections.shuffle(listaMusicaGenero);
        //Adicona musicas à nova playList até ao tamanho desejado
        for (int i = 0; i < tamanho; i++) {
            nova.adicionarMusica((Musica) listaMusicaGenero.get(i));
        }
        return nova;
    }


    /**
     * Inicia uma nova instancia de compra para o cliente.
     * Adiciona-a ao histórico de compras do cliente.
     */
    public void abrirCompra(){
        this.compra=new Compra();
        historicoCompras.add(compra);
    }

    /**
     * Verifica se uma música já foi comprada pelo cliente.
     * @param m: música a ser verificada.
     * @return true se a música já foi comprada e false caso ainda não tenha sido comprada.
     */
    public boolean estaMusicaJaExiste (Musica m){
        for (Musica mTemp : getMusicasCompradas()){
            if (m.getIdMusica().equals(mTemp.getIdMusica())){
                return true;
            }
        }
        return false;}

    public void adicionarPlayList(PlayList play){
        playlists.add(play);
    }

    @Override
    public int getTipo() {
        return 1;
    }


    public ArrayList<MusicaPaga> getMusicasCompradas() {
        return musicasCompradas;
    }


    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

    public ArrayList<Compra> getHistoricoCompras() {
        return historicoCompras;
    }
}