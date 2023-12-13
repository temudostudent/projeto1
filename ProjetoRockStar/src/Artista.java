import java.io.Serializable;
import java.util.*;

/**
 * @author cesartemudo
 * @version 1.0
 */

public class Artista extends Utilizador implements Serializable {

    //Atributos
    private int pin;
    private ArrayList<Album> albuns;
    private ArrayList<Musica> musicas;

    //Construtor que recebe username, password, pin
    public Artista(String username, String password, int pin) {
        super(username, password);
        this.pin = pin;
        this.musicas = new ArrayList<>();
        this.albuns = new ArrayList<>();
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
    public void novaMusica(String titulo, int ano, int mes, int dia, double duracao, String genero, boolean estado) {
        Musica novaM = new Musica(titulo, this, ano, mes, dia, duracao, genero, estado);
        musicas.add(novaM);
    }

    //Adiciona música a Album
    //Pesquisa na lista se o álbum existe, se existir vai procurar a música na biblioteca do artista e adiciona a esse álbum
    public void addMusica(String nomeAlbum, String tituloMusica) {
        for (Album a : albuns) {
            if (a.getNome().equalsIgnoreCase(nomeAlbum)) {   //descobrir album pelo nome
                for (Musica m : musicas) {
                    if (m.getTitulo().equalsIgnoreCase(tituloMusica)) { //descobrir musica pelo título
                        albuns.get(albuns.indexOf(a)).adicionarMusica(m);
                    }
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
}
