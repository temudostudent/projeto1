import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Compra implements Serializable{
    /**
     * @author César Temudo
     * @author Vânia Mendes
     * @version 1.0
     */
    private static int ultimoID = 0;
    private int id_compra;
    private ArrayList< MusicaPaga > carrinho;

    /**
     * Abre um novo carrinho
     */
    public Compra(){
        this.carrinho = new ArrayList<>();
        this.id_compra  = getNextId();
    }

    /**
     *
     * @return ultimoID incrementado
     */
    protected synchronized int getNextId() {
        return ++ultimoID;
    }

    /**
     *Adiciona a música apenas se já não estiver no carrinho de compras.
     * @param m
     */
    public void adicionarMusica(MusicaPaga m){
        if (!musicaEstaNoCarrinho(m)) {
            carrinho.add(m);
            JOptionPane.showMessageDialog(null, "Música '" + m.getTitulo() + "' adicionada ao carrinho");
        }else JOptionPane.showMessageDialog(null, "Música já existe no carrinho");
    }

    /**
     * Remove música que está na posição 'index'.
     * @param index
     */
    public void removerMusica(int index){
        carrinho.remove(index);
    }

    /**
     * Retorna true caso a música exista no carrinho.
     * @param m
     * @return boolean
     */
    private boolean musicaEstaNoCarrinho(MusicaPaga m){
        for (MusicaPaga mCarrinho : carrinho){
            if (mCarrinho.getIdMusica().equals(m.getIdMusica())) return true;
        }
    return false;}

    /**
     * Caso o carrinho contenha músicas, soma todos os preços e retorna esse valor.
     * @return valor total do carrinho
     */
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

    public ArrayList<MusicaPaga> getCarrinho() {
        return carrinho;
    }
}
