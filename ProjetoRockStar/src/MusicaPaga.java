import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicaPaga extends Musica implements Serializable {
    /**
     * @author César Temudo
     * @author Vânia Mendes
     * @version 1.0
     */
    private double preco;
    private Map<LocalDateTime, Double> historicoPreco = new HashMap<>();

    /**
     * Construtor de uma música que tenha preço desde a sua criação.
     * @param titulo
     * @param nomeArtista
     * @param duracao
     * @param genero
     * @param estado
     * @param preco
     */
    public MusicaPaga(String titulo, String nomeArtista, double duracao, String genero, boolean estado, double preco) {
        super(titulo, nomeArtista, duracao, genero, estado);
        this.dataCriacao = LocalDateTime.now();
        this.preco = preco;
        this.historicoPreco = new HashMap<>();
        this.rating = new HashMap<>();
        this.idMusica=getNextId();
        adicionarRegisto(preco);
    }

    /**
     * Construtor de uma música que foi gratuita na sua criação e que passou a ter um preço.
     * Os atributos atualizados transitam da música já existente.
     * @param titulo
     * @param dataCriacao
     * @param nomeArtista
     * @param duracao
     * @param genero
     * @param estado
     * @param preco
     * @param id
     * @param rating
     */
    //Construtor quando Música já existe gratuita e quer passar a ser paga
    public MusicaPaga(String titulo, LocalDateTime dataCriacao, String nomeArtista, String duracao, String genero, boolean estado, double preco, int id, Map rating) {
        this.titulo=titulo;
        this.nomeArtista=nomeArtista;
        this.genero=genero;
        this.estado=estado;
        this.duracao=duracao;
        this.dataCriacao = dataCriacao;
        this.preco = preco;
        this.historicoPreco = new HashMap<>();
        this.rating = rating;
        this.idMusica=id;
        adicionarRegisto(preco);
    }

    /**
     * Adiciona ao histórico de preços o seu novo preço e o momento (em LocalDateTime) em que foi adicionado.
     * @param novoPreco
     */
    private void adicionarRegisto(double novoPreco) {
        this.historicoPreco.put(LocalDateTime.now(), novoPreco);
    }

    public Map<LocalDateTime, Double> getHistoricoPreco() {
        return historicoPreco;
    }

    public double getPreco() {
        return preco;
    }

    /**
     * Define um novo preço e adiciona ao histórico de preços.
     * @param preco
     */
    public void setPreco(double preco) {
        if (preco < 0) {
            this.preco = 0;
        } else this.preco = preco;
        adicionarRegisto(preco);
    }

    @Override
    public boolean equals(Object m){
        if (this == m) {
            return true;
        }
        if (m == null || getClass() != m.getClass()) {
            return false;
        }
        MusicaPaga objeto = (MusicaPaga) m;
        return true;
    }
}