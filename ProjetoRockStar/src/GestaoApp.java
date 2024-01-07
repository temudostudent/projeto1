import java.io.*;
import java.util.ArrayList;

public class GestaoApp implements Serializable {
    /**
     * @author César Temudo
     * @author Vânia Mendes
     * @version 1.0
     */
    Aplicacao rockstar;
    private File fileClientes;
    private File fileArtistas;
    private File filePlayLists;
    private File fileMusicas;
    private File fileCompras;
    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    // Construtor:

    /**
     * Construtor que inicializa a aplicação e os ficheiros de objetos associados.
     */
    public GestaoApp() {
        // Inicializa a Aplicação
        this.rockstar = new Aplicacao();
        iS = null;
        // CRIA FICHEIROS:
        this.fileClientes = new File("fileClientes.dat");
        this.fileArtistas = new File("fileArtistas.dat");
        this.fileMusicas = new File("fileMusicas.dat");
        this.filePlayLists = new File("filePlaylists.dat");
        this.fileCompras = new File("fileCompras.dat");
    }

    /**
     * Método que inclui leitura e criação de ficheiro de objetos.
     */

    protected void run() {
        //File Clientes
        if (!this.fileClientes.exists()) {
            Cliente cliente = new Cliente("vania", "1234a");
            rockstar.adicionarCliente(cliente);
            //criação do ficheiro:
            try {
                this.fileClientes.createNewFile();
                oS = new ObjectOutputStream(new FileOutputStream(this.fileClientes));
                oS.writeObject(rockstar.getClientes());
                oS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Se o ficheiro existir vai ler o ficheiro
        else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileClientes));
                try {
                    this.rockstar.setClientes((ArrayList<Cliente>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {
                    System.out.println(y);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // File Artistas
        if (!this.fileArtistas.exists()) {
            Artista artista = new Artista("cesar", "1234a", "0000");
            rockstar.adicionarArtista(artista);
            try {
                this.fileArtistas.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.fileArtistas));
                oS.writeObject(rockstar.getArtistas());
                oS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileArtistas));
                try {
                    this.rockstar.setArtistas((ArrayList<Artista>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //File Músicas
        if (!this.fileMusicas.exists()) {
            try {
                this.fileMusicas.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.fileMusicas));
                oS.writeObject(rockstar.getMusicas());
                oS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileMusicas));
                try {
                    this.rockstar.setMusicas((ArrayList<Musica>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {
                    System.out.println(y);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //File Playlist
        if (!this.filePlayLists.exists()) {
            try {
                this.filePlayLists.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.filePlayLists));
                oS.writeObject(rockstar.getPlaylists());
                oS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.filePlayLists));
                try {
                    this.rockstar.setPlaylists((ArrayList<PlayList>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {
                    y.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       //File Compras
        if (!this.fileCompras.exists()) {
            try {
                this.fileCompras.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.fileCompras));
                oS.writeObject(rockstar.getCompras());
                oS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileCompras));
                try {
                    this.rockstar.setCompras((ArrayList<Compra>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {
                    System.out.println(y);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Método que é chamado em caso de logOut / fecho janela
    /**
     * Método que guarda a informação nos ficheiros de objetos.
     * @param clientes
     * @param artistas
     * @param musicas
     * @param playLists
     * @param compras
     */
    protected void atualizaficheiro(ArrayList<Cliente> clientes, ArrayList<Artista> artistas, ArrayList<Musica> musicas,
                                    ArrayList<PlayList> playLists, ArrayList<Compra> compras) {
        try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileClientes));
            oS.writeObject(clientes);
            oS.close();
        } catch (IOException e) {
            e.printStackTrace();

        }try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileArtistas));
            oS.writeObject(artistas);
            oS.close();
        } catch (IOException e) {
            e.printStackTrace();

        } try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileMusicas));
            oS.writeObject(musicas);
            oS.close();
        } catch (IOException e) {
            e.printStackTrace();

        } try {
            oS = new ObjectOutputStream(new FileOutputStream(this.filePlayLists));
            oS.writeObject(playLists);
            oS.close();
        } catch (IOException e) {
            e.printStackTrace();

        } try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileCompras));
            oS.writeObject(compras);
            oS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}