import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
    public void registarArtista(String username,String password,String pin){
        if (procurarUserArtista(username)==false && procurarUserCliente(username)==false){
            Artista novoA = new Artista(username,password,pin);
            artistas.add(novoA);
            JOptionPane.showMessageDialog(null,"Registo de Artista com sucesso!","",
                    JOptionPane.INFORMATION_MESSAGE);
        }else JOptionPane.showMessageDialog(null,"O user '" + username + "' já existe",
                "Aviso!",JOptionPane.INFORMATION_MESSAGE);;
    }

    public void registarCliente(String username,String password){
        if (procurarUserCliente(username)==false && procurarUserArtista(username)==false){
            Cliente novoC = new Cliente(username,password);
            clientes.add(novoC);
            JOptionPane.showMessageDialog(null,"Registo de Cliente com sucesso!","",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"O user '" + username + "' já existe",
                    "Aviso!",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Login - Verificar se todas as condições coincidem
    public boolean loginArtista(String username,String password,String PIN){
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

    //Verifica se o username já existe
    private boolean procurarUserArtista(String username){
        boolean existe=false;
        for (Artista a : artistas){
            if (a.getUsername().equals(username)) {
                existe=true;
            }
        }
    return existe;}

    private boolean procurarUserCliente(String username){
        boolean existe=false;
        for (Cliente c : clientes){
            if (c.getUsername().equals(username)) {
                existe=true;
            }
        }
        return existe;}

    //Gerador automático PIN
    public String gerarPin(){
        String pin;
        int num;

        Random random = new Random();
        num= 1000 + random.nextInt(9000);

        pin= String.valueOf(num);

    return pin;}




}
