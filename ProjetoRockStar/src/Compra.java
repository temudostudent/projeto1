import java.util.ArrayList;

public class Compra {
    private Integer ultimoID = 0;

    private Integer id_compra;
    private ArrayList< MusicaPaga > carrinho;

    public Compra(){
        this.carrinho = new ArrayList<>();
        this.id_compra  = ++ultimoID;
    }

    public void totalCarrinho(){
        double valorTotal = 0;
        for(MusicaPaga m : carrinho){
            valorTotal += m.getPreco();
        }
    }
    public void limparCarrinho(){
        carrinho.clear();
    }

    public ArrayList<MusicaPaga> getCarrinho() {
        return carrinho;
    }
}
