import java.io.Serializable;
import java.util.*;

/**
 * @author cesartemudo
 * @version 1.0
 */

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private String pin;
    private ArrayList<Album> albuns;
    private ArrayList<Musica> musicas;

    //Construtor que recebe username, password, pin
    public Artista(String username, String password, String pin) {
        super(username, password);
        this.pin = pin;
        this.musicas = new ArrayList<>();
        this.albuns = new ArrayList<>();
        super.saldo=0;
    }

    //Imprimir todos os albuns do artista
    @Override
    public void verListas() {
        albuns.forEach(System.out::println);
    }

    //Cria um novo objeto Album e adiciona à lista do Artista, recebe o nome do Album e o género
    public void criarAlbum(String nomeDoAlbum, String genero) {
        Album novoA = new Album(nomeDoAlbum, this, genero);
        albuns.add(novoA);
    }

    //Criar música e adiciona automaticamente à biblioteca de músicas do artista
    public void novaMusica(String titulo, int ano, int mes, int dia, double duracao, String genero, boolean estado,double preco) {
        Musica novaM=null;
        if (preco<=0){
            novaM = new Musica(titulo,this.username,ano,mes,dia,duracao,genero,estado);
        }else {
            novaM = new MusicaPaga(titulo,this,ano,mes,dia,duracao,genero,estado,preco);
        }
        musicas.add(novaM);
    }

    //Adiciona música a Album
    //Pesquisa na lista se o álbum existe, se existir vai procurar a música na biblioteca do artista e adiciona a esse álbum
    public void addMusica(String nomeAlbum, String tituloMusica) {
        int cont = 0;
        for (Album a : albuns) {
            if (a.getNome().equalsIgnoreCase(nomeAlbum)) {   //descobrir album pelo nome
                for (Musica m : musicas) {
                    if (m.getTitulo().equalsIgnoreCase(tituloMusica)) { //descobrir musica pelo título
                        albuns.get(albuns.indexOf(a)).adicionarMusica(m);
                        cont++;
                    }
                }
                if (cont == 1) {
                    System.out.println("Música '" + tituloMusica + "' adicionada com sucesso ao álbum '" + nomeAlbum + "'");
                    break;
                }
            } else {
                System.out.println("Álbum '" + nomeAlbum + "' não encontrado");
            }
        }
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

    public String getPin() {
        return pin;
    }

    public int getTipo(){
        return 2;
    }
}
