import java.io.*;
import java.util.ArrayList;

public class GestaoApp implements Serializable {
    //meti privado em vez de publico como no banco
    Aplicacao rockstar;
    private File fileClientes;
    private File fileArtistas;
    private File filePlayLists;
    private File fileMusicas;
    private File fileCompras;
    private ObjectInputStream iS;
    private ObjectOutputStream oS;

    // Construtor:
    public GestaoApp() {
        // INICIALIZA A APP:
        this.rockstar = new Aplicacao();
        iS = null;
        // CRIA FICHEIROS:
        this.fileClientes = new File("fileClientes.dat");
        this.fileArtistas = new File("fileArtistas.dat");
        this.fileMusicas = new File("fileMusicas.dat");
        this.filePlayLists = new File("filePlaylists.dat");
        this.fileCompras = new File("fileCompras.dat");
    }

    public Aplicacao getRockstar() {
        return rockstar;
    }


    public void setRockstar(Aplicacao rockstar) {
        this.rockstar = rockstar;
    }


    //METODO RUN()  INCLUI AUTOMATICAMENTE LEITURA E CRIACAO DOS FICHEIROS
    @SuppressWarnings("unchecked")
    protected void run() {

        //File Clientes
        if (!this.fileClientes.exists()) {
            Cliente cliente = new Cliente("vania", "1234a");

            // De seguida � efectuada a criação do ficheiro:
            try {
                this.fileClientes.createNewFile();
                oS = new ObjectOutputStream(new FileOutputStream(this.fileClientes));
                oS.writeObject(rockstar.getClientes());
                oS.close();
            } catch (IOException e) {
                System.out.println(e);

            }
        }
        //FICHEIRO EXISTE ---> ler o ficheiro existente
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
                System.out.println(e);

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
                System.out.println(e);

            }
        }
        //CASO 2:FICHEIRO EXISTE --> ler o ficheiro
        else {

            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileArtistas));
                try {
                    this.rockstar.setArtistas((ArrayList<Artista>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {

                }
            } catch (IOException e) {
                System.out.println(e);

            }

        }
        //File Músicas
        if (!this.fileMusicas.exists()) {
            Musica musica = new Musica("Hero", "beyonce", 3.4, "rock", true);
            rockstar.adicionarMusica(musica);

            try {
                this.fileMusicas.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.fileMusicas));
                oS.writeObject(rockstar.getMusicas());
                oS.close();
            } catch (IOException e) {

            }
        }
        //CASO 2:FICHEIRO EXISTE -- > lê o ficheiro existente
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

        }

        //File Playlist

        if (!this.filePlayLists.exists()) {
            try {
                this.filePlayLists.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.filePlayLists));
                oS.writeObject(rockstar.getPlaylists());
                oS.close();
            } catch (IOException e) {

            }
        }
        //CASO 2:FICHEIRO EXISTE -- > lê o ficheiro existente
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

       //File Compras
        if (!this.fileCompras.exists()) {


            try {
                this.fileCompras.createNewFile();
                this.oS = new ObjectOutputStream(new FileOutputStream(this.fileCompras));
                oS.writeObject(rockstar.getCompras());
                oS.close();
            } catch (IOException e) {

            }
        }
        else {
            try {
                this.iS = new ObjectInputStream(new FileInputStream(this.fileCompras));
                try {
                    this.rockstar.setCompras((ArrayList<Compra>) iS.readObject());
                    iS.close();
                } catch (ClassNotFoundException y) {

                }
            } catch (IOException e) {
            }
        }
    }

    //GUARDAR OS FICHEIROS
    //CHAMADO em caso de logOut / fecho janela
    protected void atualizaficheiro(ArrayList<Cliente> clientes, ArrayList<Artista> artistas, ArrayList<Musica> musicas,
                                    ArrayList<PlayList> playLists, ArrayList<Compra> compras) {
        try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileClientes));
            oS.writeObject(clientes);
            oS.close();
        } catch (IOException e) {

        }

        try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileArtistas));
            oS.writeObject(artistas);
            oS.close();
        } catch (IOException e) {

        }
        try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileMusicas));
            oS.writeObject(musicas);
            oS.close();
        } catch (IOException e) {

        }
        try {
            oS = new ObjectOutputStream(new FileOutputStream(this.filePlayLists));
            oS.writeObject(playLists);
            oS.close();
        } catch (IOException e) {

        }
        try {
            oS = new ObjectOutputStream(new FileOutputStream(this.fileCompras));
            oS.writeObject(compras);
            oS.close();
        } catch (IOException e) {
        }
    }
}
