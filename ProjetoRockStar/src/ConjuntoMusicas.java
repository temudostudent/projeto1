import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConjuntoMusicas implements Serializable {
    /**
     * @author César Temudo
     * @author Vânia Mendes
     * @version 1.0
     */
    protected String nome;
    protected ArrayList<Musica> musicas;
    protected LocalDateTime dataCriacao;

    /**
     * Construtor de uma classe que armazena um conjunto de músicas, tem um nome e uma data que é registada no momento da criação.
     * @param nome
     */
    public ConjuntoMusicas(String nome) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        musicas=new ArrayList<>();
    }

    /**
     * Remove a música que se situa na posição "index".
     * @param index
     */
    public void removeMusica(int index){
        musicas.remove(index);
    }

    /**
     * Adiciona a música ao final da lista.
     * @param m Musica
     */
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Formata data para aparecer com o padrão "yyyy-MM-dd".
     * @return data formatada
     */
    public String getDataCriacao() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dataCriacao.format(dataFormatada);
    }
}