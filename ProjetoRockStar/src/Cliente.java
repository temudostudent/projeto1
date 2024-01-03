import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// criação classe Cliente
public class Cliente extends Utilizador implements Serializable {

    //Atributos
    private Integer idCliente;
    private Integer ultimoID = 0;

    private ArrayList<PlayList> playlists;
    private ArrayList<Compra> historicoCompras;

    private ArrayList<Musica>musicasCompradas;
    protected Compra compra;


    // Construtor classe
    public Cliente(String username,String password) {
        super(username, password);
        this.playlists = new ArrayList<>();
        this.historicoCompras = new ArrayList<>();
        super.saldo = 0;
        this.musicasCompradas=new ArrayList<>();
        this.idCliente = ultimoID++;
    }

    public Cliente(){}

    public void criarPlaylist(String nomeDaLista, boolean visibilidade) {
        PlayList nova = new PlayList(nomeDaLista, visibilidade);
        playlists.add(nova);
    }
    public void adicionarMusicaComprada( Musica m){
        musicasCompradas.add(m);
    }

    public ArrayList<Musica> getMusicasCompradas() {
        return musicasCompradas;
    }

    //Método para verificar se já existe uma playlist com o mesmo nome
    public boolean verificarNomePlaylist(String nome){
        boolean nomeOK = true;
        for(PlayList play : playlists){
            if (play.getNome().equals(nome)) {
               nomeOK = false;
            }else{
                nomeOK = true;
            }
        }
        return nomeOK;
    }
    public ArrayList<PlayList> verPlayListCliente(){
        return playlists;
    }

    // Método para adicionar músicas a uma determinada PlayList
    public void adicionarMusica(PlayList p, Musica m) {
        p.adicionarMusica(m);
    }

    //Encontra uma playlist pelo nome
    public PlayList pesquisaPlaylistTitulo(String titulo){
        PlayList objeto = null;
        for(PlayList p : playlists){
            if(p.getNome().equals(titulo)){
                objeto = p;
            }
        }
        return objeto;
    }

    //Cria uma playlist com todas as musicas do mesmo genero
    public ArrayList listaMusicaGenero(String genero, ArrayList <Musica> listaMusicasGlobal){
        ArrayList <Musica> musicasGenero = new ArrayList<>();
        for(Musica musica: listaMusicasGlobal ) {
            if (musica.getGenero().equalsIgnoreCase(genero)) {
                if(musica instanceof Musica) {
                    musicasGenero.add(musica);
                }
            }
        }return musicasGenero;
    }

    public void removerMusicaPlayList(){
        removerMusicaPlayList();
    }


    // Criar uma playList indicando o género e tamanho
    public void criarPlayListGenero(String titulo, String genero, int tamanho, ArrayList listaMusicaGenero) {
        PlayList nova = new PlayList(titulo, true);

        // Cria uma lista com musicas do genero selecionado
        ArrayList<Musica> lmusicasGenero = new ArrayList<>();


            //Baralhar a lista de musicas do genero
            Collections.shuffle(listaMusicaGenero);

            //Adicona musicas à nova playList até ao tamanho desejado
            for (int i = 0; i < tamanho; i++) {
                nova.adicionarMusica((Musica)listaMusicaGenero.get(i));

        //Adicionar a nova playList à lista das playLists
        playlists.add(nova);
        }
    }

    //Remover playList através do objeto
    public void removerPlaylist(PlayList playlist) {
        playlists.remove(playlist);
    }

    //
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

    //Método para finalizar a compra
    public void finalizarcompra(){
        if(compra.getCarrinho().isEmpty()){
            System.out.println("Carrinho Vazio. Não é possível finalizar a compra.");
            return;
        }
        //Deduzir o preço do saldo do Cliente
        double saldoAtual;
        //saldoAtual =  getSaldo() - compra.totalCarrinho();
        //setSaldo(saldoAtual);

        //Adicionar compra ao historico
        historicoCompras.add(compra);

        //Esvaziar carrinho
        compra.limparCarrinho();

    }

    // alterar saldo do cliente informando a quantidade
    public void alterarSaldo(double quantia) {
        saldo += quantia;
    }

    @Override
    public int getTipo() {
        return 1;
    }

    public Integer getIdCliente() {
        return idCliente;
    }


    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

}

