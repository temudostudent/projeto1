import javax.swing.*;
import java.io.Serializable;
import java.util.*;

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
    public void registarArtista(String username, String password, String pin) {
        if (procurarUserArtista(username) == false && procurarUserCliente(username) == false) {
            Artista novoA = new Artista(username, password, pin);
            artistas.add(novoA);
            JOptionPane.showMessageDialog(null, "Registo de Artista com sucesso!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(null, "O user '" + username + "' já existe",
                "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        ;
    }

    public void registarCliente(String username, String password) {
        if (procurarUserCliente(username) == false && procurarUserArtista(username) == false) {
            Cliente novoC = new Cliente(username, password);
            clientes.add(novoC);
            JOptionPane.showMessageDialog(null, "Registo de Cliente com sucesso!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "O user '" + username + "' já existe",
                    "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Login - Verificar se todas as condições coincidem

    public boolean loginCliente(String username, String password) {
        boolean login = false;
        if (procurarUserCliente(username)) {
            for (Cliente c : clientes) {
                if (c.getPassword().equals(password)) {
                    login = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Password Incorreta. Tente novamente!", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
        return login;
    }

    public boolean loginArtista(String username, String password) {
        boolean login = false;
        if (procurarUserArtista(username)) {
            for (Artista a : artistas) {
                if (a.getPassword().equals(password)) {
                    login = true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password Incorreta. Tente novamente!", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
        return login;
    }

    public boolean verificarPINArtista(String username,String pin){
        boolean login = false;
        int cont=0;
        if (procurarUserArtista(username)) {
            for (Artista a : artistas) {
                if (a.getUsername().equals(username) && a.getPin().equals(pin)) {
                    login = true;
                    cont++;
                }
            }
            if (cont==1){
                JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Pin Incorreto. Tente novamente!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return login;
    }


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

        Random random = new Random();
        int num= 1000 + random.nextInt(9000);

        pin= String.valueOf(num);

    return pin;}

    public int tipoUtilizador(String username) {
        int tipo=0;
        for (Cliente c : clientes) {
            if (c.getUsername().equals(username)) {
                tipo=c.getTipo();
            }
        }
        for (Artista a : artistas) {
            if (a.getUsername().equals(username)) {
                tipo=a.getTipo();
            }
        }
        return tipo;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public void setPlaylists(ArrayList<PlayList> playlists) {
        this.playlists = playlists;
    }
}
