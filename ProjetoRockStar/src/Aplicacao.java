import javax.swing.*;
import java.io.Serializable;
import java.util.*;

public class Aplicacao implements Serializable {

    //Atributos
    private ArrayList<Cliente> clientes;
    private ArrayList<Artista> artistas;
    private ArrayList<Musica> musicas;
    private ArrayList<PlayList> playlists;
    private ArrayList<Compra> compras;

    //Construtor
    public Aplicacao() {
        this.clientes = new ArrayList<Cliente>();
        this.artistas = new ArrayList<Artista>();
        this.musicas = new ArrayList<Musica>();
        this.playlists = new ArrayList<PlayList>();
        this.compras = new ArrayList<Compra>();

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

    public Cliente registarCliente(String username, String password) {
        if (procurarUserCliente(username) == false && procurarUserArtista(username) == false) {
            if (validarPassword(password)) {
                Cliente novoC = new Cliente(username, password);
                clientes.add(novoC);

                JOptionPane.showMessageDialog(null, "Registo de Cliente com sucesso!", "",
                        JOptionPane.INFORMATION_MESSAGE);
                return novoC;
            } else {
                JOptionPane.showMessageDialog(null, "A senha não atende aos critérios de segurança.",
                        "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "O usuário '" + username + "' já existe",
                    "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    //Login - Verificar se todas as condições coincidem

    public Cliente loginCliente(String username, String password) {
        if (!procurarUserCliente(username)) {
            JOptionPane.showMessageDialog(null, "Utilizador não existe!", "",
                    JOptionPane.INFORMATION_MESSAGE);
            return null;

        } else {
            for (Cliente c : clientes) {
                if (c.getPassword().equals(password)) {
                    //Se a password estiver correta devolve o cliente
                    return c;
                }
            }
            //Password incorreta retorna null
            JOptionPane.showMessageDialog(null, "Credencias inválidas. Tente novamente!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }


    public Artista loginArtista(String username, String password) {
        Artista artista = null;
        if (procurarUserArtista(username)) {
            for (Artista a : artistas) {
                if (a.getPassword().equals(password)) {
                    artista=a;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password Incorreta. Tente novamente!", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        return artista;
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

    public void addMusica(Musica m){
        musicas.add(m);
    }
    public boolean validarPassword(String password){
        //Tamanho entre 4 e 6 caracteres
        if(password.length() < 4 || password.length() > 6){
            return false;
        }
        //Tem de ter pelo menos uma letra e um numero
        boolean temLetra = false;
        boolean temNumero = false;

        for(char c : password.toCharArray()){
            if(!Character.isLetter(c)){
                temLetra = true;
            }else if(!Character.isDigit(c)){
                temNumero  =true;
            }
        }
        return temLetra && temNumero;
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
    public void ordenarDecrescentePorTitulo(){
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

    public boolean procurarUserCliente(String username){
        for (Cliente c : clientes){
            if (c.getUsername().equals(username)) {
                return true;
            }
        }
    return false;}

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

    //Métodos de ORDENAÇÃO

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

    public ArrayList<Musica> listaMusicasDeTitulo(String titulo){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                novaLista.add(m);
            }
        }
        return novaLista;
    }

    public ArrayList<Musica> listaMusicasDeArtista(String artista){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Musica m : musicas){
            if(m.getNomeArtista().equalsIgnoreCase(artista)){
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

    public ArrayList<Compra> getCompras() {
        return compras;
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

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    public void adicionarCliente(Cliente c){
        clientes.add(c);
    }
    public void adicionarMusica(Musica m){
        musicas.add(m);
    }
    public void removerMusica(Musica m){musicas.remove(m);}
    public void adicionarPlayList(PlayList p){
        playlists.add(p);
    }
    public void adicionarArtista(Artista a){
        artistas.add(a);
    }
    public void adicionarCompra(Compra com) {compras.add(com);}

}
