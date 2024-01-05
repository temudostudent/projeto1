import javax.swing.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author cesartemudo
 * @version 1.0
 */

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private Integer idArtista;
    private Integer ultimoID = 0;
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
        this.idArtista = ultimoID++;
    }

    //Cria um novo objeto Album e adiciona à lista do Artista, recebe o nome do Album e o género
    public void criarAlbum(String nomeDoAlbum, String genero) {
        Album novoA = new Album(nomeDoAlbum, this.username, genero);
        albuns.add(novoA);
    }

    //Criar música e adiciona automaticamente à biblioteca de músicas do artista
    public Musica novaMusica(String titulo, double duracao, String genero, boolean estado, double preco) {
        Musica novaM = null;
        String tituloMaiuscula = titulo.substring(0, 1).toUpperCase() + titulo.substring(1);
        if (preco <= 0) {
            novaM = new Musica(tituloMaiuscula, this.username, duracao, genero, estado);
            JOptionPane.showMessageDialog(null, "Musica grátis criada!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            novaM = new MusicaPaga(tituloMaiuscula, this.username, duracao, genero, estado, preco);
            JOptionPane.showMessageDialog(null, "Musica com o preço " + preco + " criada!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return novaM;
    }

    public void addMusica(Musica m){
        musicas.add(m);
    }

    //Pesquisar música pelo titulo
    public ArrayList<Musica> pesquisarMusica(String titulo){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                novaLista.add(m);
            }
        }
        return novaLista;
    }

    public Musica pesquisaObjetoTitulo(String titulo){
        Musica objeto = null;
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                objeto = m;
            }
        }
        return objeto;
    }

    //Pesquisar Musica por Género
    public ArrayList<Musica> pesquisarMusicaGenero(String genero) {
        ArrayList<Musica> novaLista = new ArrayList<>();
        for (Musica m : musicas) {
            if (m.getGenero().equalsIgnoreCase(genero)) {
                novaLista.add(m);
            }
        }
        return novaLista;
    }

    //Posicao na ArrayList a partir do nome da musica
    public int encontrarPosicao(Musica m){
        int posicao = 0;
        for(Musica musica : musicas){
            if(musica.equals(m)){
                posicao = musicas.indexOf(musica);
            }
        }
        return posicao;
    }

    //Pesquisar um álbum e listar todas as músicas
    public ArrayList<Musica> pesquisaMusicaAlbum(String tituloAlbum){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Album album : albuns){
            novaLista.addAll(album.getMusicas());
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

    public void alterarSaldo(double quantia) {
        saldo += quantia;
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

    public void ordenarMusicasCrescentePorTitulo(ArrayList lista){

        //Criar um comparador
        Comparator<Musica> comparador = Comparator.comparing(Musica::getTitulo);
        //Ordenar lista usando o comparador
        lista.sort(comparador);
    }

    public void ordendarMusicasDecrescentePorTitulo(ArrayList lista){

        Comparator<Musica> comparador = Comparator.comparing(Musica::getTitulo).reversed();
        lista.sort(comparador);
    }

    public void ordenarMusicasCrescentePorGenero(ArrayList lista){

        Comparator<Musica> comparador = Comparator.comparing(Musica::getGenero);
        lista.sort(comparador);
    }

    public void ordenarMusicasDecrescentePorGenero(ArrayList lista){

        Comparator<Musica> comparador = Comparator.comparing(Musica::getGenero).reversed();
        lista.sort(comparador);
    }

    public void ordenarAlbunsCrescentePorTitulo(ArrayList lista){

        Comparator<Album> comparador = Comparator.comparing(Album::getNome);
        lista.sort(comparador);
    }

    public void ordendarAlbunsDecrescentePorTitulo(ArrayList lista){

        Comparator<Album> comparador = Comparator.comparing(Album::getNome).reversed();
        lista.sort(comparador);
    }

    public void ordenarAlbunsCrescentePorGenero(ArrayList lista){

        Comparator<Album> comparador = Comparator.comparing(Album::getGenero);
        lista.sort(comparador);
    }

    public void ordenarAlbunsDecrescentePorGenero(ArrayList lista){

        Comparator<Album> comparador = Comparator.comparing(Album::getGenero).reversed();
        lista.sort(comparador);
    }

    public Integer getIdArtista() {
        return idArtista;
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
