import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class PlayList extends ConjuntoMusicas implements Serializable {
    /**
     * @author César Temudo
     * @author Vânia Mendes
     * @version 1.0
     */
    private int idPlaylist;
    private String criador;
    private static int ultimoId = 0;
    private boolean visibilidade;

    /**
     * Construtor de um conjunto de músicas que pode, ou não, ser visível para outros utilizadores.
     * @param nome nome da playlist
     * @param visibilidade
     * @param username nome do criador
     */
    public PlayList(String nome, boolean visibilidade, String username) {
        super(nome);
        this.visibilidade = visibilidade;
        this.idPlaylist = getNextId();
        this.criador = username;
    }

    /**
     * Gera número de identificação único para cada nova música.
     * @return ultimoID incrementado
     */
    private static synchronized int getNextId() {
        return ++ultimoId;
    }

    /**
     * Verifica se pelo menos uma música da lista referenciada como parâmetro já existe nesta playlist, retornando true nesse caso.
     * @param lista
     * @return boolean
     */
    public boolean musicasJaExistem (ArrayList<Musica> lista){
        for (Musica m: lista) {
            for (Musica mPl : getMusicas()){
                if (m.getIdMusica().equals(mPl.getIdMusica())){
                    JOptionPane.showMessageDialog(null, "A música '" + m.getTitulo() + "' já existe na playlist");
                    return true;
                }
            }
        }
    return false;}

    /**
     * Retorna true caso a música exista nesta playlist.
     * @param m Música
     * @return boolean
     */
    public boolean estaMusicaJaExiste (Musica m){
        for (Musica mPl : getMusicas()){
            if (m.getIdMusica().equals(mPl.getIdMusica())){
                return true;
        }
    }
    return false;}

    public String getCriador() {
        return criador;
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }

    /**
     *
     * @return número de músicas da playlist
     */
    public int getNumMusicas(){
        return this.musicas.size();
    }

    //Mostra a visibilidade
    public boolean isVisibilidade() {
        return this.visibilidade;
    }

    /**
     *
     * @return visibilidade "pública" ou "privada"
     */
    public String getVisibilidade() {
        if (visibilidade) {
            return "   PÚBLICA";
        } else {
            return "   PRIVADA";
        }
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "idPlaylist=" + idPlaylist +
                ", visibilidade=" + visibilidade +
                '}';
    }
}
