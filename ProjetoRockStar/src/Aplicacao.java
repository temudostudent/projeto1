import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Aplicacao implements Serializable {

    //Atributos
    private ArrayList<Cliente> clientes;
    private ArrayList<Artista> artistas;
    private ArrayList<Musica> musicas;
    private ArrayList<PlayList> playlists;

    //Construtor
    public Aplicacao() {
        this.clientes = new ArrayList<Cliente>();
        this.artistas = new ArrayList<Artista>();
        this.musicas = new ArrayList<Musica>();
        this.playlists = new ArrayList<PlayList>();
    }

    //Registar - Verifica primeiro se username já existe, se não existir cria o objeto
    public void registarArtista(String username,String password,int pin){
        if (verificaSeExiste(username)==false){
            Artista novoA = new Artista(username,password,pin);
            artistas.add(novoA);
        }else System.out.println("O username '" + username + "' já existe");
    }

    public void registarCliente(String username,String password){
        if (verificaSeExiste(username)==false){
            Cliente novoC = new Cliente(username,password);
            clientes.add(novoC);
        }else System.out.println("O username '" + username + "' já existe");
    }

    //Login - Verificar se todas as condições coincidem
    public boolean loginArtista(String username,String password,int PIN){
        boolean login=false;
        int cont=0, a=0;
        do {
            artistas.get(a);
                if (artistas.get(a).getUsername().equals(username) &&
                        artistas.get(a).getPassword().equals(password) &&
                        artistas.get(a).getPin() == PIN) {
                    login = true;
                    cont++;
                }
            a++;
        }while (cont==0);
    return login;}

    public boolean loginCliente(String username, String password){
        boolean login=false;
        int cont=0, c=0;
        do {
            clientes.get(c);
            if (artistas.get(c).getUsername().equals(username) &&
                    artistas.get(c).getPassword().equals(password)) {
                login = true;
                cont++;
            }
            c++;
        }while (cont==0);
        return login;}

    //Verifica se o username já existe
    private boolean verificaSeExiste(String username){
        boolean existe=false;
        int cont=0, a=0, c=0;
        do {
            artistas.get(a);
                if (artistas.get(a).getUsername().equals(username)) {
                    existe = true;
                    cont++;
                }
            a++;
        }while (cont==0);
        do {
           clientes.get(c);
                if (clientes.get(c).getUsername().equals(username)) {
                    existe = true;
                    cont++;
                }
            c++;
        } while (cont == 0);
    return existe;}

    //Listar músicas
    public void listarMusicas(){
        musicas.forEach(System.out::println);
    }

    //Listar playlists
    public void listarPlaylists(){
        playlists.forEach(System.out::println);
    }

    //Ordenar a lista de musicas pelo título de forma ascendente
    public void ordenarCrescentePorTitulo(){
        //Criar um comparador
        Comparator<Musica> comparador = Comparator.comparing(Musica::getTitulo);
        //Ordenar lista usando o comparador
        musicas.sort(comparador);
    }

    //Ordenar a lista de musicas pelo título de forma descendente
    public void ordendarDecrescentePorTitulo(){
        Comparator<Musica> comparador = Comparator.comparing(Musica::getTitulo).reversed();
        musicas.sort(comparador);
    }


}
