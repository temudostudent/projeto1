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
    public String toString() {
        if (visibilidade) {
            return nome + ",   PÚBLICA";
        } else {
            return nome + ",   PRIVADA";
        }
    }



    //Adiciona música
    @Override
    public void adicionarMusica(Musica m) {
        this.musicas.add(m);
    }

    //Remove música
    @Override
    public void removeMusica(Musica m){
        this.musicas.remove(m);
    }

    public boolean musicasJaExistem (ArrayList<Musica> lista){
        for (Musica m: lista) {
            if (getMusicas().contains(m)) {
                JOptionPane.showMessageDialog(null, "A música '" + m.getTitulo() + "' já existe na playlist");
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
}
