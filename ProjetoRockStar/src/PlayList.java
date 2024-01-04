import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class PlayList extends ConjuntoMusicas implements Serializable {
    private Integer idPlaylist;
    private Integer ultimoID = 0;
    private  boolean visibilidade;


    public PlayList(String nome, boolean visibilidade) {
        super(nome);
        this.visibilidade = visibilidade;
        this.idPlaylist = ultimoID++;
    }
    public String getVisibilidade() {
        if (visibilidade) {
            return "   PÚBLICA";
        } else {
            return "   PRIVADA";
        }
    }

    public Musica pesquisaMusicaTitulo(String titulo){
        Musica musica = null;
        for(Musica m : musicas){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                musica = m;
            }
        }
        return musica;
    }


    //Adiciona música
    @Override
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }


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


    public boolean estaMusicaJaExiste (Musica m){
        for (Musica mPl : getMusicas()){
            if (m.getIdMusica().equals(mPl.getIdMusica())){
                return true;
        }
    }
    return false;}


    public String getNome() {
        return this.nome;
    }

    public Integer getIdPlaylist() {
        return idPlaylist;
    }

    public int getNumMusicas(){return this.musicas.size();}

    //Mostra a visibilidade
    public boolean isVisibilidade() {
        return this.visibilidade;
    }

    //Set visibilidade
    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "idPlaylist=" + idPlaylist +
                ", ultimoID=" + ultimoID +
                ", visibilidade=" + visibilidade +
                '}';
    }
}
