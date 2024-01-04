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
        Cliente cliente = null;
        if (procurarUserCliente(username)) {
            for (Cliente c : clientes) {
                if (c.getPassword().equals(password)) {
                    //Se a password estiver correta devolve o cliente
                    cliente = c;
                }else{
                    JOptionPane.showMessageDialog(null, "Password Incorreta. Tente novamente!", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else {
            JOptionPane.showMessageDialog(null, "Utilizador não encontrado!", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return cliente;
    }


    public Artista loginArtista(String username, String password) {
        Artista artista = null;
        if (procurarUserArtista(username)) {
            for (Artista a : artistas) {
                if (a.getPassword().equals(password)) {
                    artista = a;
                }
            }
                if (artista == null){
                    JOptionPane.showMessageDialog(null, "Password Incorreta. Tente novamente!", "",
                            JOptionPane.INFORMATION_MESSAGE);

                }
        }else{
                JOptionPane.showMessageDialog(null, "Utilizador não encontrado!", "",
                        JOptionPane.INFORMATION_MESSAGE);
        }
        return artista;
    }

    public boolean verificarPINArtista(String username,String pin){

        if (procurarUserArtista(username)) {
            for (Artista a : artistas) {
                if (a.getUsername().equals(username) && a.getPin().equals(pin)) {
                    JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!", "",
                            JOptionPane.INFORMATION_MESSAGE);
                    return true;

                }
            }

                JOptionPane.showMessageDialog(null, "Pin incorreto! Tente novamente", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Utilizador não encontrado!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        return false;
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

    //Método que retorna o tipo de utilizador

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

    //Encontrar objeto música
    public Musica estaMusica(Musica m){
        for (Musica musica : musicas){
            if (musica.equals(m)){
                return musica;
            }
        }
    return null;}
    //Méotodo criar lista genero gratuitas
    public ArrayList<Musica> musicasGratuitasGenero(String genero){
        ArrayList<Musica> novaLista = new ArrayList<>();

        for(Musica m : musicas){
            if(m.getGenero().equalsIgnoreCase(genero) && !(m instanceof MusicaPaga)){
                    novaLista.add(m);

            }
        }
        return novaLista;
    }

    //Métodos de ORDENAÇÃO
    public void ordenarMusicasCrescentePorTitulo(ArrayList lista){

        //Criar um comparador
        Comparator<Musica> comparador = Comparator.comparing(Musica::getTitulo);
        //Ordenar lista usando o comparador
        lista.sort(comparador);
    }

    public void ordenarMusicasDecrescentePorTitulo(ArrayList lista){

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

    public int encontrarPosicao(Musica m){
        int posicao = 0;
        for(Musica musica : musicas){
            if(musica.equals(m)){
                posicao = musicas.indexOf(musica);
            }
        }
        return posicao;
    }

    public void pagarArtistas(ArrayList<MusicaPaga> carrinho){
        for (MusicaPaga musica : carrinho){
            for (Artista a : artistas){
                if (a.getUsername().equals(musica.getNomeArtista())){
                    a.alterarSaldo(musica.getPreco());
                }
            }
        }
    }

    //Quantificar utilizadores que têm pelo menos 1 música de um artista
    public int totalUtilizadores(Artista a){
        int ut=0;
        for (Cliente c : clientes){
            if (ouveArtista(c,a)){
                ut++;
            }
        }
    return ut;}

    //Saber se o Cliente tem pelo menos 1 música de um determinado artista
    private boolean ouveArtista(Cliente c, Artista a){
        for (PlayList pl: clientes.get(indexCliente(c)).getPlaylists()){
            for (Musica m : pl.getMusicas()){
                if (m.getNomeArtista().equals(a.getUsername())){
                    return true;
                }
            }
        }
    return false;}

    //local do Cliente na Arraylist
    private int indexCliente(Cliente c){
        int index=0;
        for (int i=0;i<clientes.size();i++){
            if (clientes.get(i).getIdCliente().equals(c.getIdCliente())){
                index=i;
                break;
            }
        }
    return index;}

    //Título da música mais comprada
    public String musicaMaisComprada(Artista a){
        String nomeMusica=new String();
        int contMax=0;
        for (Musica m : a.getMusicas()){
            if (m instanceof MusicaPaga){
                int cont=0;
                for (Cliente c : clientes) {
                    for (MusicaPaga mp : c.getMusicasCompradas()){
                        if (mp.getIdMusica().equals(m.getIdMusica()))
                            cont++;
                    }
                }
                if (cont>contMax){
                    contMax=cont;
                    nomeMusica=m.getTitulo();
                }
            }
        }
        return nomeMusica + " - " + contMax + " vezes";}

    //Título da música mais selecionada
    public String musicaMaisAdicionada(Artista a){
        String nomeMusica=new String();
        int contMax=0;
        for (Musica m : a.getMusicas()){
            int cont=0;
            for (Cliente c : clientes) {
                for (PlayList pl : c.getPlaylists()){
                    if (musicaExisteNaPlaylist(m, pl)) {
                        cont++;
                    }
                }
            }
            System.out.println(m.getTitulo() + " ," + m.getIdMusica() + " ," + cont);
            if (cont>contMax){
                contMax=cont;
                nomeMusica=m.getTitulo();
            }
        }
    return nomeMusica + " - " + contMax + " vezes";}

    //Saber se música está na playlist
    private boolean musicaExisteNaPlaylist(Musica m, PlayList pl){
        if (!pl.getMusicas().isEmpty()){
            for (Musica mtemp : pl.getMusicas()) {
                if (mtemp.getIdMusica().equals(m.getIdMusica())) {
                    return true;
                }
            }
        }
    return false;}

    public void removePlaylistPorID (int id){
        for (PlayList pl : playlists){
            if (pl.getIdPlaylist().equals(id)){
                playlists.remove(pl);
            }
        }
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

    public void removerPlayListID (int id){
        for(PlayList play : playlists){
            if(play.getIdPlaylist() == id){
                playlists.remove(play);
            }
        }
    }
    public void removerPlayList(PlayList p){ playlists.remove(p);}
    public void adicionarCompra(Compra com) {compras.add(com);}

}
