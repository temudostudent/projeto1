import javax.swing.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author cesartemudo
 * @version 1.0
 */

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private String pin;
    private ArrayList<Album> albuns = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();

    //Construtor que recebe username, password, pin
    public Artista(String username, String password, String pin) {
        super(username, password);
        this.pin = pin;
        this.musicas = musicas;
        this.albuns = albuns;
        super.saldo = 0;
    }

    public Artista() {
    }

    //Imprimir todos os albuns do artista
    @Override
    public void verListas() {
        albuns.forEach(System.out::println);
    }

    //Cria um novo objeto Album e adiciona à lista do Artista, recebe o nome do Album e o género
    public void criarAlbum(String nomeDoAlbum, String genero) {
        Album novoA = new Album(nomeDoAlbum, this.username, genero);
        albuns.add(novoA);
        JOptionPane.showMessageDialog(null, "Álbum criado!", "",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //Criar música e adiciona automaticamente à biblioteca de músicas do artista
    public void novaMusica(String titulo, int ano, int mes, int dia, double duracao, String genero, boolean estado, double preco) {
        Musica novaM = null;
        if (preco <= 0) {
            novaM = new Musica(titulo, this.username, ano, mes, dia, duracao, genero, estado);
            JOptionPane.showMessageDialog(null, "Musica gratis criada!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            novaM = new MusicaPaga(titulo, this.username, ano, mes, dia, duracao, genero, estado, preco);
            JOptionPane.showMessageDialog(null, "Musica com o preço " + preco + " criada!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        musicas.add(novaM);
    }


    //Pesquisar musica pelo titulo
    public ArrayList<Musica> pesquisarMusica(String titulo){
        ArrayList novaLista = new ArrayList<>();
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                novaLista.add(m.getTitulo());
            }
        }
        return novaLista;
    }

    //Adiciona música a Album
    //Pesquisa na lista se o álbum existe, se existir vai procurar a música na biblioteca do artista e adiciona a esse álbum
    public void addMusica(int indexAlbum, int indexMusica) {
        Musica m = musicas.get(indexMusica);

        if (!albuns.get(indexAlbum).musicas.contains(m)){
            albuns.get(indexAlbum).adicionarMusica(m);
            JOptionPane.showMessageDialog(null, "Musica adicionada com sucesso", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }else JOptionPane.showMessageDialog(null, "Musica não foi adicionada", "",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //Remover música de Album
    public void removeMusica(String nomeAlbum, String tituloMusica) {
        for (Album a : albuns) {
            if (a.getNome().equalsIgnoreCase(nomeAlbum)) {   //descobrir album pelo nome
                albuns.get(albuns.indexOf(a)).removeMusica(a.encontraMusica(tituloMusica));
            } else {
                System.out.println("Álbum '" + nomeAlbum + "' não encontrado");
            }
        }
    }

    //Altera o título
    public void corrigirTitulo(Musica m, String novoTitulo){
        m.setTitulo(novoTitulo);
    }

    //Altera o preço
    public void alterarPreco(MusicaPaga m, double novoPreco){
        m.setPreco(novoPreco);
    }

    //Número total de músicas
    public int totalMusicas(){
        return musicas.size();
    }

    //Valor total das músicas que tem na coleção neste momento
    public double valorTotalColecao(){
        double soma=0;
        for (int i=0;i<musicas.size();i++){
            if (musicas.get(i) instanceof MusicaPaga){
                soma+=((MusicaPaga) musicas.get(i)).getPreco();
            }
        }
    return soma;
    }

    //Valor total de todas as músicas vendidas
    public double valorTotalVendas(){
        return getSaldo();
    }

    //Número total de álbuns
    public int totalAlbuns(){
        return albuns.size();
    }

    //Inativar música m
    public void inativarMusica(Musica m){
        m.setEstado(false);
    }

    //Número total de álbuns do género X
    public int totalAlbunsGenero(String genero){
        int cont=0;
        for (Album a : albuns){
            if (a.getGenero().equalsIgnoreCase(genero)){
                cont++;
            }
        }
    return cont;
    }

    //Método para criar uma lista com todos os generos do artista
    private ArrayList listaGeneros(){
        ArrayList<String> listaGeneros = new ArrayList<>();
        for (Album album : albuns) {
            String genero = album.getGenero();

            // Verifica se o género ainda não está na lista e caso não estaja, adiciona
            if (!listaGeneros.stream().anyMatch(g -> g.equalsIgnoreCase(genero))) {
                listaGeneros.add(genero);
            }
        }
        return listaGeneros;
    }

    //Método para devolver o numero de géneros de álbuns que o artista possui
    private int numeroGeneros(){
        ArrayList novaLista = listaGeneros();
        return novaLista.size();
    }

    public String [][] matrizTotalAlbuns(){
        int linhas = numeroGeneros();
        String [][] nova = new String[linhas][2];
        ArrayList <String> listaGeneros1 = listaGeneros();

        for(int i = 0; i < nova.length; i++){
                nova[i][0] = listaGeneros1.get(i);
                nova [i][1] = String.valueOf(totalAlbunsGenero(listaGeneros1.get(i)));
            }
        return nova;
    }


    public String getPin() {
        return pin;
    }

    public int getTipo(){
        return 2;
    }

    public ArrayList<Album> getAlbuns() {
        return albuns;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }
}
