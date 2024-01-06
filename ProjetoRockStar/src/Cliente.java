import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cliente extends Utilizador implements Serializable {

    //Atributos
    private ArrayList<PlayList> playlists;
    private ArrayList<Compra> historicoCompras;
    private ArrayList<MusicaPaga> musicasCompradas;
    protected Compra compra;

    // Construtor classe
    public Cliente(String username,String password) {
        super(username, password);
        this.playlists = new ArrayList<>();
        this.historicoCompras = new ArrayList<>();
        super.saldo = 0;
        this.musicasCompradas=new ArrayList<>();
    }

    public PlayList criarPlaylist(String nomeDaLista, boolean visibilidade) {
        PlayList nova = new PlayList(nomeDaLista, visibilidade, this.username);
        return nova;
    }
    public ArrayList<MusicaPaga> getMusicasCompradas() {
        return musicasCompradas;
    }
    //Método para verificar se já existe uma playlist com o mesmo nome
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
    // Método para adicionar músicas a uma determinada PlayList
    public void adicionarMusica(PlayList p, Musica m) {
        p.adicionarMusica(m);
    }
    //Encontra uma playlist pelo nome
    public PlayList pesquisaPlaylistTitulo(String titulo) {
        PlayList objeto = null;
        for (PlayList p : playlists) {
            if (p.getNome().equals(titulo)) {
                objeto = p;
            }
        }
        return objeto;
    }
    //Cria uma playlist com todas as musicas do mesmo genero
    public ArrayList listaMusicaGenero(ArrayList<Musica> listaMusicasGratuitas) {
        ArrayList<Musica> musicasGenero = listaMusicasGratuitas;
        for (Musica musica : musicasCompradas) {
            musicasGenero.add(musica);
        }
        return musicasGenero;
    }
    // Criar uma playList indicando o género e tamanho
    public PlayList criarPlayListGenero(String titulo, int tamanho, ArrayList listaMusicaGenero) {
        PlayList nova = new PlayList(titulo, true, this.username);

        //Baralhar a lista de musicas do genero
        Collections.shuffle(listaMusicaGenero);
        //Adicona musicas à nova playList até ao tamanho desejado
        for (int i = 0; i < tamanho; i++) {
            nova.adicionarMusica((Musica) listaMusicaGenero.get(i));
        }
        return nova;
    }
    public void adicionarPlayList(PlayList play){
        playlists.add(play);
    }
    public void abrirCompra(){
        this.compra=new Compra();
        historicoCompras.add(compra);
    }
    public boolean estaMusicaJaExiste (Musica m){
        for (Musica mTemp : getMusicasCompradas()){
            if (m.getIdMusica().equals(mTemp.getIdMusica())){
                return true;
            }
        }
        return false;}
    @Override
    public int getTipo() {
        return 1;
    }
    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }
}