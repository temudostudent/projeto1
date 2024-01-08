import javax.swing.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author cesartemudo
 * @autor Vânia Mendes
 * @version 1.0
 */

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private String pin;
    private ArrayList<Album> albuns ;
    private ArrayList<Musica> musicas;

    /**
     * Construtor da classe que recebe um username passwor e pin, cria um conjunto
     * de música e de álbuns e atribui o saldo nulo.
     *
     * @param username
     * @param password
     * @param pin
     */
    public Artista(String username, String password, String pin) {
        super(username, password);
        this.pin = pin;
        this.musicas = new ArrayList<>();
        this.albuns = new ArrayList<>();
        super.saldo = 0;
    }


    /**
     * Cria um novo objeto álbum e adiciona à lista do Artista
     * @param nomeDoAlbum
     * @param genero
     */
    public void criarAlbum(String nomeDoAlbum, String genero) {
        //Cria novo album
        Album novoA = new Album(nomeDoAlbum, this.username, genero);
        //Adicona à lista de albuns do artista
        albuns.add(novoA);
    }

    /**
     * Verifica se o título da música já existe na lista de músicas do artista.
     * @param titulo da música a ser verificado.
     * @return true se o título já existe, false caso não exista.
     */
    private boolean tituloJaExiste(String titulo){
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                return true;
            }
        }
        return false;
    }


    /**
     *Cria uma nova música e adiciona à lista de músicas do artista.
     * @param titulo da musica a ser criada.
     * @param duracao da musica em minutos.
     * @param genero da musica.
     * @param estado da musica. Indica se é publica ou privada.
     * @param preco Se for menor ou igual a zero a musica é considerada gratuita.
     * @return a instância da música criada, ou null se o título já existir.
     */
    public Musica novaMusica(String titulo, double duracao, String genero, boolean estado, double preco) {
        Musica novaM = null;
        String tituloMaiuscula = titulo.substring(0, 1).toUpperCase() + titulo.substring(1);

        //Verifica se o titulo já existe na lista de músicas do artista.
        if (!tituloJaExiste(tituloMaiuscula)) {

            //Cria uma nova instancia da Musica ou MusicaPaga, dependendo do preço
            if (preco <= 0) {
                novaM = new Musica(tituloMaiuscula, this.username, duracao, genero, estado);
                JOptionPane.showMessageDialog(null, "Musica grátis criada!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                novaM = new MusicaPaga(tituloMaiuscula, this.username, duracao, genero, estado, preco);
                JOptionPane.showMessageDialog(null, "Musica com o preço " + preco + " criada!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //Caso o titulo da música já exista
            JOptionPane.showMessageDialog(null, "Titulo musica já existe. Escolha novo título" , "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return novaM;
    }

    /**
     * Adiciona uma música à lista de músicas do artista.
     * @param m: nova musica a ser adicionada.
     */
    public void addMusica(Musica m){
        musicas.add(m);
    }

    /**
     * Pesquisa músicas na lista do artista com base no título fornecido
     * @param titulo da musica a ser pesquisado
     * @return uma lista com todas as instancias de músicas encontrados com o mesmo título.
     */
    public ArrayList<Musica> pesquisarMusica(String titulo){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                novaLista.add(m);
            }
        }
        return novaLista;
    }

    /**
     * Pesquisa a instancia de uma musica pelo titulo
     * @param titulo da musica a ser pesquisada.
     * @return a instancia da musica encontrada, ou null caso não encontre.
     */
    public Musica pesquisaObjetoPorTitulo(String titulo){
        Musica objeto = null;
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                objeto = m;
            }
        }
        return objeto;
    }

    /**
     * Pesquisa a instancia musica pelo género.
     * @param genero da musica a ser pesquisada.
     * @return lista contendo todas as instanciqa de músicas encontradas com o mesmo género.
     */
    public ArrayList<Musica> pesquisarMusicaPorGenero(String genero) {
        ArrayList<Musica> novaLista = new ArrayList<>();
        for (Musica m : musicas) {
            if (m.getGenero().equalsIgnoreCase(genero)) {
                novaLista.add(m);
            }
        }
        return novaLista;
    }

    /**
     * Pesquisa todas as musicas associadas a um album através do titulo.
     * @param tituloAlbum a ser fornecido.
     * @return lista contendo todaas as musicas encontradas nos albuns com o tituolo indicado.
     */
    public ArrayList<Musica> pesquisaMusicaAlbum(String tituloAlbum){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Album album : albuns){
            novaLista.addAll(album.getMusicas());
        }
        return novaLista;
    }

    /**
     * Adiciona uma música a um album com base nos indices fornecidos.
     * @param indexAlbum: indice do album na lista de albuns do artista.
     * @param indexMusica: indice da musica na lista de musicas do artista.
     */
    public void addMusicaAAlbumPelosIndexes(int indexAlbum, int indexMusica) {
        //Encontra a instancia atraves do indice fornecido.
        Musica m = musicas.get(indexMusica);
        //Verifica se o album ja contem a musica
        if (!albuns.get(indexAlbum).musicas.contains(m)){
            //Caso o album ainda nao tenho a musica esta á adicionado ao album.
            albuns.get(indexAlbum).adicionarMusica(m);
            JOptionPane.showMessageDialog(null, "Musica adicionada com sucesso", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }else JOptionPane.showMessageDialog(null, "Musica não foi adicionada", "",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Devolve o numero total de músicas na lista de artista.
     * @return numero total de músicas.
     */
    public int totalMusicas(){
        return musicas.size();
    }

    /**
     * Calcula o valor total da coleção de musicas com preço do artista.
     * @return valor total da coleção do artista.
     */
    public double valorTotalColecao(){
        double soma=0;
        for (int i=0;i<musicas.size();i++){
            if (musicas.get(i) instanceof MusicaPaga){
                soma+=((MusicaPaga) musicas.get(i)).getPreco();
            }
        }
    return soma;
    }

    /**
     * Contabiliza o numero total de albuns de um determinado género.
     * @param genero fornecido dos albuns a serem contabilizados.
     * @return numero total de albuns do género.
     */
    public int totalAlbunsGenero(String genero){
        int cont=0;
        for (Album a : albuns){
            if (a.getGenero().equalsIgnoreCase(genero)){
                cont++;
            }
        }
    return cont;
    }

    /**
     * Cria uma lista de géneros presentes nos albuns do artista.
     * @return lista contendo os generos dos albuns(sem repeticões,ignorando
     * maiúsculas e minúsculas.
     */
    private ArrayList listaGeneros(){
        ArrayList<String> listaGeneros = new ArrayList<>();
        for (Album album : albuns) {
            String genero = album.getGenero();
            // Verifica se o género ainda não está na lista e caso não esteja, adiciona
            if (!listaGeneros.stream().anyMatch(g -> g.equalsIgnoreCase(genero))) {
                listaGeneros.add(genero);
            }
        }
        return listaGeneros;
    }

    /**
     * Retorna o número total de géneros presentes nos albuns do artista.
     * @return numero total géneros.
     */
    private int numeroGeneros(){
        ArrayList novaLista = listaGeneros();
        return novaLista.size();
    }

    /**
     * Cria uma matriz contendo o total de albuns por género.
     * @return matriz contendo o genero de album e o  numero total de albuns desse género.
     */

    public String [][] matrizTotalAlbuns(){
        int linhas = numeroGeneros();
        String [][] nova = new String[linhas][2];
        ArrayList <String> listaGeneros1 = listaGeneros();

        for(int i = 0; i < nova.length; i++){
                nova[i][0] = listaGeneros1.get(i);
                nova[i][1] = String.valueOf(totalAlbunsGenero(listaGeneros1.get(i)));
            }
        return nova;
    }

    /**
     * Atualiza os ratings das músicas com base numa lista fornecida
     * @param lista de musicas contendo os novos ratings.
     */
    public void atualizarRatings(ArrayList<Musica>lista){
        for (Musica m:musicas){
            for (Musica mLista:lista){
                if (m.getIdMusica()==mLista.getIdMusica()){
                    m.setRating(mLista.getRating());
                }
            }
        }
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