import java.io.*;
import java.util.ArrayList;

public class GestaoApp implements Serializable {

    Aplicacao rockstar;

    private File fileClientes;
    private File fileArtistas;
    private File fileMusicas;
    private File filePlayLists;
    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    //Construtor
    public GestaoApp() {
        this.rockstar = new Aplicacao();
        iS = null;

        //Cria ficheiros
        this.fileArtistas = new File("fileArtistas.dat");
        this.fileClientes = new File("fileclientes.dat");
        this.fileMusicas = new File("fileMusicas.dat");
        this.filePlayLists = new File("filePlayists.dat");
    }

    protected void run() {
        //Criar ficheiros de utilizador

        //Caso não exista um ficheiro de utilizadores serão criados 2 user por defeito
        if (!this.fileArtistas.exists()) {
            rockstar.registarArtista("vania", "1234", "0000");

            try {
                this.fileArtistas.createNewFile();
                oS = new ObjectOutputStream(new FileOutputStream(this.fileArtistas));
                oS.writeObject(rockstar.getArtistas());
                oS.close();
            } catch (IOException e) {
            }

        }
        //Caso ja exista o ficheiro faz-se a transferencia de dados
        else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileArtistas));
                try {
                    this.rockstar.setArtistas((ArrayList<Artista>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {

                }
            } catch (IOException e) {

            }
        }

        if (!this.fileClientes.exists()) {
            rockstar.registarCliente("Cesar", "1234");
            try {
                this.fileClientes.createNewFile();
                oS = new ObjectOutputStream(new FileOutputStream(this.fileClientes));
                oS.writeObject(rockstar.getClientes());
                oS.close();
            } catch (IOException e) {

            }
        } else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileClientes));
                try {
                    this.rockstar.setClientes((ArrayList<Cliente>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {

                }
            } catch (IOException e) {

            }
        }

        //Caso não exista ficheiro criado
        if (!this.fileMusicas.exists()) {

            try {
                this.fileMusicas.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.fileMusicas));
                oS.writeObject(rockstar.getMusicas());
                oS.close();
            } catch (IOException e) {

            }
        }
        // caso ja exista vou passar a informa�ao do ficheiro para a classe:
        else {

            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileMusicas));
                try {
                    this.rockstar.setMusicas((ArrayList<Musica>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {

                }
            } catch (IOException e) {

            }

            // Playlist
            if (!this.filePlayLists.exists()) {

                try {
                    this.filePlayLists.createNewFile();
                    this.oS = new ObjectOutputStream(new FileOutputStream(this.filePlayLists));
                    oS.writeObject(rockstar.getPlaylists());
                    oS.close();
                } catch (IOException e) {

                }
            }
            // caso ja exista vou passar a informa�ao do ficheiro para a classe:
            else {

                try {
                    this.iS = new ObjectInputStream(new FileInputStream(this.filePlayLists));
                    try {
                        this.rockstar.setPlaylists((ArrayList<PlayList>) iS.readObject());
                        iS.close();
                    } catch (ClassNotFoundException y) {

                    }
                } catch (IOException e) {

                }

            }
        }
    }
            // metodo que guarda os ficheiros:
            // este metedo � chamado quando qualquer uma das janelas tipo adm/clt/fun sao
            // fechadas ou efectuado logout.

            protected void atualizaficheiro(ArrayList < Artista > artistas, ArrayList < Cliente > clientes, ArrayList < PlayList > playlists,
                    ArrayList < Musica > musicas){
                try {
                    oS = new ObjectOutputStream(new FileOutputStream(this.fileArtistas));
                    oS.writeObject(artistas);
                    oS.close();
                } catch (IOException e) {

                }

                try {
                    oS = new ObjectOutputStream(new FileOutputStream(this.fileClientes));
                    oS.writeObject(clientes);
                    oS.close();

                } catch (IOException e) {

                }

                try {
                    oS = new ObjectOutputStream(new FileOutputStream(this.filePlayLists));
                    oS.writeObject(playlists);
                    oS.close();

                } catch (IOException e) {

                }
                try {
                    oS = new ObjectOutputStream(new FileOutputStream(this.fileMusicas));
                    oS.writeObject(musicas);
                    oS.close();

                } catch (IOException e) {

                }

        }
    }

















}
