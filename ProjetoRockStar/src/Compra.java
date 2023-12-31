import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Compra implements Serializable{
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

    public void adicionarMusica(MusicaPaga m){
        if (!musicaEstaNoCarrinho(m)) {
            carrinho.add(m);
            JOptionPane.showMessageDialog(null, "Música '" + m.getTitulo() + "' adicionada ao carrinho");
        }else JOptionPane.showMessageDialog(null, "Música já existe no carrinho");
    }

    public void removerMusica(MusicaPaga m){
        carrinho.remove(m);
    }

    private boolean musicaEstaNoCarrinho(MusicaPaga m){
        for (MusicaPaga mCarrinho : carrinho){
            if (mCarrinho.equals(m)) return true;
        }
    return false;}

    public double totalCarrinhoCliente(){
        double valorTotal = 0;
        if (carrinho.size()==0){
            return 0;
        }else {
            for (MusicaPaga m : carrinho) {
                valorTotal += m.getPreco();
            }
        }
    return valorTotal;}

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
