import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicaPaga extends Musica implements Serializable {

    private double preco;
   private Map<LocalDateTime, Double> historicoPreco = new HashMap<>();

    public MusicaPaga(String titulo, String nomeArtista, double duracao, String genero, boolean estado, double preco) {
        super(titulo, nomeArtista, duracao, genero, estado);
        this.dataCriacao = LocalDateTime.now();
        this.preco = preco;
        this.historicoPreco = historicoPreco;
        adicionarRegisto(preco);
    }
    //Construtor quando Música já existe gratuita e quer passar a ser paga
    public MusicaPaga(String titulo, String dataCriacao, String nomeArtista, double duracao, String genero, boolean estado, double preco) {
        super(titulo, nomeArtista, duracao, genero, estado);
        this.dataCriacao = LocalDateTime.parse(dataCriacao);
        this.preco = preco;
        this.historicoPreco = historicoPreco;
        adicionarRegisto(preco);
    }

    private void adicionarRegisto(double novoPreco){
        this.historicoPreco.put(LocalDateTime.now(),novoPreco);
    }


    public Map<LocalDateTime, Double> getHistoricoPreco() {
        return historicoPreco;
    }

    public double getPreco() {return preco;}

    public void setPreco(double preco) {
        if (preco<0){
            this.preco=0;
        }else this.preco = preco;

        adicionarRegisto(preco);
    }
    @Override
    public String tipoEstado(){
        if(getEstado() == true){
            return "ATIVA";
        }else{
            return "INATIVA";
        }
    }
}
