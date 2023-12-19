import java.util.ArrayList;

public class Compra {
    private Integer ultimoID = 0;

    private Integer id_compra;
    private ArrayList< MusicaPaga > carrinho;

    public Compra(){
        this.carrinho = new ArrayList<>();
        this.id_compra  = ++ultimoID;
    }

    public void criarCarrinho(){
        Compra compra = new Compra();
    }

    public void totalCarrinhoCliente(){
        double valorTotal = 0;
        for(MusicaPaga m : carrinho){
            valorTotal += m.getPreco();
        }
    }

    public void valorMusicaArtista(){
        double valorMusica = 0;
        double saldoArtista = 0;
        Artista a;
        for(MusicaPaga m : carrinho){
            valorMusica = m.getPreco();

        }
    }
    public void limparCarrinho(){
        carrinho.clear();
    }

    public ArrayList<MusicaPaga> getCarrinho() {
        return carrinho;
    }
}
