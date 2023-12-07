import java.io.Serializable;

public abstract class Utilizador implements Serializable {
    protected String username;
    protected String password;
    protected double saldo;

    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public abstract void verListas();
    public abstract void criarListaMusicas();

}
