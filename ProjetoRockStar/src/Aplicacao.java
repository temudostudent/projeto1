import javax.swing.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author César Temudo
 * @author Vânia Mendes
 * @version 1.0
 */

public class Aplicacao implements Serializable {

    //Atributos
    private ArrayList<Cliente> clientes;
    private ArrayList<Artista> artistas;
    private ArrayList<Musica> musicas;
    private ArrayList<PlayList> playlists;
    private ArrayList<Compra> compras;

    /**
     * Construtor da classe Aplicação que inicia as listas de clientes, artista, músicas, playlists e compras.
     */
    public Aplicacao() {
        this.clientes = new ArrayList<Cliente>();
        this.artistas = new ArrayList<Artista>();
        this.musicas = new ArrayList<Musica>();
        this.playlists = new ArrayList<PlayList>();
        this.compras = new ArrayList<Compra>();
    }

    /**
     * Regista um novo artista na aplicação.
     * @param username: do usuário único do artista.
     * @param password: senha associada ao username do artista.
     * @param pin: PIN do artista.
     */
    public void registarArtista(String username, String password, String pin) {
        //Verifica se o nome de usuário já existe como artista ou cliente.
        if (!verificaUserArtista(username) && !verificaUserCliente(username)) {
            //Valida a senha inserida relativamente aos critérios de segurança
            if(validarPassword(password)) {
                //Caso não exista cria uma nova instância de Artista.
                Artista novoA = new Artista(username, password, pin);
                //Adiciona o novo artista à lista de artistas.
                artistas.add(novoA);
                JOptionPane.showMessageDialog(null, "Registo de Artista com sucesso!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "A senha não atende aos critérios de segurança.",
                        "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //Caso o nome do usuário já exista exibe uma mensagem ao tutilizador
            JOptionPane.showMessageDialog(null, "O user '" + username + "' já existe",
                    "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Regista um novo cliente na aplicação.
     * @param username: nome do usuário único do cliente.
     * @param password: senha associada ao username do cliente.
     */
    public void registarCliente(String username, String password) {
        if (!verificaUserCliente(username) && !verificaUserArtista(username)) {
            if (validarPassword(password)) {
                Cliente novoC = new Cliente(username, password);
                clientes.add(novoC);
                JOptionPane.showMessageDialog(null, "Registo de Cliente com sucesso!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "A senha não atende aos critérios de segurança.",
                        "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "O usuário '" + username + "' já existe",
                    "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Realiza o login de um cliente na aplicação
     * @param username do cliente.
     * @param password associada ao username do cliente.
     * @return instancia do cliente cujo login foi efetuado com sucesso se as credenciais estiverem corretas, ou null caso estejam incorretas.
     */
    public Cliente loginCliente(String username, String password) {
        //Inicializa a variável cliente como null
        Cliente cliente = null;
        //Verifica se o nome do usuário pertende a um cliente
        if (verificaUserCliente(username)) {
            for (Cliente c : clientes) {
                //Verifica se as credenciais estão corretas
                if (c.getPassword().equals(password) && c.getUsername().equals(username)) {
                    cliente = c;
                    break;
                }
                //Verifica se foi encontrado um cliente com as credenciais
            }if (cliente != null && cliente.getPassword().equals(password)) {// Se a password estiver correta devolve o cliente
                return cliente;
            } else {
                JOptionPane.showMessageDialog(null, "Password Incorreta. Tente novamente!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return null;
    }

    /**
     * Realiza o login de artista na aplicação
     * @param username do artista.
     * @param password associada ao nome do artista.
     * @return a instância ao artista cujo login foi efetuado com sucesso se as credenciais estiverem corretas, ou null caso estejam incorretas.
     */

    public Artista loginArtista(String username, String password) {
        Artista artista = null;
        if (verificaUserArtista(username)) {
            for (Artista a : artistas) {
                if (a.getPassword().equals(password) && a.getUsername().equals(username)) {
                    artista = a;
                    break;
                }
            }if (artista != null && artista.getPassword().equals(password)) {
                // Se a password estiver correta devolve o cliente
                return artista;
            } else {
                JOptionPane.showMessageDialog(null, "Password Incorreta. Tente novamente!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return null;
    }

    /**
     * Verifica o PIN de um artista durante o processo de login.
     * @param username do artista.
     * @param pin associado ao artista.
     * @return true se o PIN estiver correto e false se o PIN estiver incorreto.
     */

    public boolean verificarPINArtista(String username,String pin){
        //Verifica se o nome do usuario pertence a um artistas.
        if (verificaUserArtista(username)) {
            for (Artista a : artistas) {
                //Verifica se o nome de usuário e o PIN correspondem a um artista.
                if (a.getUsername().equals(username) && a.getPin().equals(pin)) {
                    JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!", "",
                            JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            }
                JOptionPane.showMessageDialog(null, "Pin incorreto! Tente novamente", "",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Utilizador não encontrado!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        //Retorna false se o PIN estiver incorreto ou se o ususário não for encontrado.
        return false;
    }

    /**
     * Valida a senha de acesso com critérios específicos: deve ter entre 4 e 6 caracteres, entre eles pelo menos uma letra e um numero.
     * @param password a ser avaliada.
     * @return true se a senha atender aos critérios e false caso contrário.
     */

    public boolean validarPassword(String password){
        //Tamanho entre 4 e 6 caracteres
        if(password.length() < 4 || password.length() > 6){
            return false;
        }
        //Tem de ter pelo menos uma letra e um número.
        boolean temLetra = false;
        boolean temNumero = false;

        for(char c : password.toCharArray()){
            //Verifica se o caracter é uma letra.
            if(!Character.isLetter(c)){
                temLetra = true;
                //Verifica se o caracter é um numero.
            }else if(!Character.isDigit(c)){
                temNumero  =true;
            }
        }
        //Retorn true se a senha contiver pelo menos uma letra e um número.
        return temLetra && temNumero;
    }

    /**
     * Verifica se o nome de um usuário pertence a um artista.
     * @param username Nome de usuário a ser verificado
     * @return true se o nome de usuário pertencer a um artista e false caso não pertença.
     */
    private boolean verificaUserArtista(String username){
        for (Artista a : artistas){
            if (a.getUsername().equals(username)) {
                return true;
            }
        }
    return false;}

    /**
     * Verifica se um nome de ususário pertence a um cliente.
     * @param username Nome do usuário a ser verificado.
     * @return true se o nome de usuário pertencer a um cliente e false caso não pretença.
     */
    private boolean verificaUserCliente(String username){
        for (Cliente c : clientes){
            if (c.getUsername().equals(username)) {
                return true;
            }
        }
    return false;}

    /**
     * Gera um PIN aleatório de 4 dígitos.
     * @return PIN gerado como uma String.
     */
    public String gerarPin(){
        String pin;
        Random random = new Random();
        int num= 1000 + random.nextInt(9000);
        pin= String.valueOf(num);
    return pin;}

    /**
     *Verifica o tipo de utilizador (Cliente ou Artista) com base no username
     * @param username Nome de ususário a ser verificado.
     * @return O tipo de utilizador: 1 para Cliente, 2 para Artista e 0 se não for encontrado.
     */
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

    /**
     * Cria uma lista de músicas gratuitas de um determinado género.
     * @param genero Género de músicas a serem filtradas.
     * @return Lista de músicas gratuitas do género pretendido.
     */
    public ArrayList<Musica> musicasGratuitasGenero(String genero){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Musica m : musicas){
            //Verifica se a música pertence ao género pretendido e se é gratuita.
            if(m.getGenero().equalsIgnoreCase(genero) && !(m instanceof MusicaPaga)){
                    novaLista.add(m);
            }
        }
        return novaLista;
    }

    /**
     * Ordena uma lista de músicas em ordem crescente com base no título.
     * @param lista A lista de músicas a ser ordenada.
     */
    public void ordenarMusicasCrescentePorTitulo(ArrayList lista){
        //Criar um comparador
        Comparator<Musica> comparador = Comparator.comparing(Musica::getTitulo);
        //Ordenar lista usando o comparador
        lista.sort(comparador);
    }

    /**
     * Ordena a lista de músicas em ordem decrescente com base no título
     * @param lista A lista de músicas a ser ordenada
     */
    public void ordenarMusicasDecrescentePorTitulo(ArrayList lista){
        Comparator<Musica> comparador = Comparator.comparing(Musica::getTitulo).reversed();
        lista.sort(comparador);
    }

    /**
     * Ordena uma lista de músicas por ordem crescente com base no género.
     * @param lista Lista de músicas a ser ordenada.
     */
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
    public void ordenarAlbunsDecrescentePorTitulo(ArrayList lista){
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

    /**
     * Cria uma lista de músicas com um título fornecido
     * @param titulo Título das músicas a serem filtradas.
     * @return Lista de músicas com o título fornecido.
     */
    public ArrayList<Musica> listaMusicasDeTitulo(String titulo){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                novaLista.add(m);
            }
        }
        return novaLista;
    }

    /**
     * Retorna uma lista de músicas do artista pretendido.
     * @param artista Nome do artista pretendido.
     * @return Lista de músicas do artista pretendido.
     */

    public ArrayList<Musica> listaMusicasDeArtista(String artista){
        ArrayList<Musica> novaLista = new ArrayList<>();
        for(Musica m : musicas){
            if(m.getNomeArtista().equalsIgnoreCase(artista)){
                novaLista.add(m);
            }
        }
        return novaLista;
    }

    /**
     * Pesquisa e devolve uma instância de música com o titulo pretendido.
     * @param titulo Título da música a ser pesquisada.
     * @return A instância de música com o título pretendido, ou null caso não seja encontrada.
     */
    public Musica pesquisaObjetoTitulo(String titulo){
        Musica objeto = null;
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                objeto = m;
            }
        }
        return objeto;
    }

    /**
     * Realiza o pagamento aos artistas das músicas presentes no carrinho.
     * @param carrinho Lista de músicas pagas.
     */
    public void pagarArtistas(ArrayList<MusicaPaga> carrinho){
        for (MusicaPaga musica : carrinho){
            for (Artista a : artistas){
                if (a.getUsername().equals(musica.getNomeArtista())){
                    //Realiza a alteração no saldo do artista de acordo com o preço da música.
                    a.alterarSaldo(musica.getPreco());
                }
            }
        }
    }

    /**
     * Devolve o total de utilizadores que ouvem um determinado artista.
     * @param a Artista a ser considerado.
     * @return Número total de utilizadores que ouvem o artista.
     */
    public int totalUtilizadores(Artista a){
        int ut=0;
        for (Cliente c : clientes){
            if (ouveArtista(c,a)){
                ut++;
            }
        }
    return ut;}

    /**
     * Verifica se um cliente ouve um determinado artista através das playlists.
     * @param c O cliente a ser verificado.
     * @param a O artista a ser considerado.
     * @return true se o cliente ouve o artista e false caso contrário.
     */
    private boolean ouveArtista(Cliente c, Artista a){
        //Percorre as playlists do cliente.
        for (PlayList pl: clientes.get(indexCliente(c)).getPlaylists()){
            //Percorre as músicas da playlist.
            for (Musica m : pl.getMusicas()){
                //Verifica se o nome do artista da música é igual ao username do artista.
                if (m.getNomeArtista().equals(a.getUsername())){
                    return true;
                }
            }
        }
    return false;}

    /**
     * Devolve o indice de um cliente na lista de clientes
     * @param c cliente a ser procurado.
     * @return O índice do cliente na lista, ou -1 caso não seja encontrado.
     */
    private int indexCliente(Cliente c){
        int index=0;
        for (int i=0;i<clientes.size();i++){
            if (clientes.get(i).getUsername().equals(c.getUsername())){
                index=i;
                break;
            }
        }
    return index;}

    /**
     * Devolve o título da música mais comprada de um determinado artista.
     * @param a Artista a ser considerado.
     * @return String formatada contendo o título da música mais comprada eo numero de vezes que foi comprada.
     */
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

    /**
     * Devolve o título da música mais adicionada às playlists dos clientes pertencente a um determinado artista.
     * @param a O artista considerado.
     * @return String formatada contendo o título da música mais adicionada e o numero de vezes que foi adicionada.
     */
    public String musicaMaisAdicionada(Artista a){
        String nomeMusica=new String();
        int contMax=0;
        for (Musica m : a.getMusicas()){
            int cont=0;
            for (Cliente c : clientes) {
                for (PlayList pl : c.getPlaylists()){
                    if (pl.estaMusicaJaExiste(m)) {
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
    public void adicionarMusica(Musica m){
        musicas.add(m);
    }
    public void adicionarPlayList(PlayList p){
        playlists.add(p);
    }
    public void adicionarArtista(Artista a){
        artistas.add(a);
    }
    public void adicionarCliente(Cliente c){
        clientes.add(c);}

    /**
     * Remover uma playlist com base no seu Id.
     * @param id Id da playlista a ser removida.
     */
    public void removerPlayListID (int id){
        for (int i=0;i<getPlaylists().size();i++){
            if (getPlaylists().get(i).getIdPlaylist()==id){
                playlists.remove(i);
            }
        }
    }

    /**
     * Atualiza o título de uma música pretendida através do seu id.
     * @param id Id da música a ser atualizada..
     * @param novoTitulo O novo título a ser atribuído à música.
     */
    public void atualizarTituloMusica (int id,String novoTitulo){
        for (int i=0;i<getMusicas().size();i++){
            if (getMusicas().get(i).getIdMusica()==id){
                musicas.get(i).setTitulo(novoTitulo);
            }
        }
    }

    /**
     * Inativa uma música com base no seu ID, removendo-a da lista de músicas global.
     * @param id Id da música a remover.
     */
    public void inativarMusica (int id){
        for (int i=0;i<getMusicas().size();i++){
            if (getMusicas().get(i).getIdMusica()==id){
                musicas.remove(i);
            }
        }
    }

    /**
     * Atualiza preço da música pretendida
     * @param id Id da música que cujo preço será atualizado.
     * @param novoPreco O novo preço a ser atribuído à música.
     */
    public void atualizarPrecoMusica (int id,double novoPreco){
        for (int i=0;i<getMusicas().size();i++){
            if (getMusicas().get(i).getIdMusica()==id){
                ((MusicaPaga)musicas.get(i)).setPreco(novoPreco);
            }
        }
    }

    /**
     * Encontra a posição de uma música numa lista através do seu Id.
     * @param id Id da música a ser procurada.
     * @return A posição da música na lista ou -1 caso não seja encontrada.
     */
    public int encontrarPosicao (int id){
        int pos=-1;
        for (int i=0;i<musicas.size();i++){
            if (musicas.get(i).getIdMusica()==id){
                pos=i;
            }
        }
    return pos;}


    /**
     * Altera a visibilidade de uma playlist através do seu Id.
     * @param id Id da playlist a ter a visibilidade alterada.
     * @param visibilidade Nova visibilidade a ser atribuída à playlist.
     */
    public void alterarVisibilidade (int id, boolean visibilidade){
        for(PlayList play : playlists){
            if(play.getIdPlaylist() == id){
                play.setVisibilidade(visibilidade);
            }
        }
    }
}