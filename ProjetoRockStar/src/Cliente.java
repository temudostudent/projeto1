import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// criação classe Cliente
public class Cliente extends Utilizador implements Serializable {

    //Atributos

    private ArrayList<PlayList> playlists=new ArrayList<>();
    private ArrayList<Compra> historicoCompras=new ArrayList<>();
    protected Compra compra;


    // Construtor classe
    public Cliente(String username,String password) {
        super(username, password);
        this.playlists = playlists;
        this.historicoCompras = historicoCompras;
        super.saldo = 0;
    }

    public Cliente(){}

    public void criarPlaylist(String nomeDaLista, boolean visibilidade) {
        PlayList nova = new PlayList(nomeDaLista, visibilidade);
        playlists.add(nova);
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


    // Criar uma playList indicando o género e tamanho
    public void criarPlayListGenero(String genero, int tamanho, ArrayList <Musica> listaMusicas) {
        String nome = genero + "list";
        PlayList nova = new PlayList(genero+"list", true);

        // Cria uma lista com musicas do genero selecionado
        ArrayList<Musica> musicasGenero = new ArrayList<>();
        //Percorre a lista de musicas global e adiciona as musicas do genero selecionado
        for(Musica musica: listaMusicas ) {
            if (musica.getGenero().equals(genero)) {
                musicasGenero.add(musica);
            }
        }

        //Baralhar a lista de musicas do genero
        Collections.shuffle(musicasGenero);

        //Adicona musicas à nova playList até ao tamanho desejado
        int cont = 0;
        do {
            for (Musica musica : musicasGenero) {
                nova.adicionarMusica(musica);
                cont++;
            }
        }while(cont<tamanho);

        //Adicionar a nova playList à lista das playLists
        playlists.add(nova);
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

    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

    public void verPlayListCliente(int indiceSelecionado) {
    }
}

