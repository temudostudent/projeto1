import java.io.Serializable;

public abstract class Utilizador implements Serializable {
    protected String username;
    protected String password;
    protected double saldo;

    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Utilizador (){}

    public abstract int getTipo();
    public abstract void alterarSaldo(double quantia);

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getSaldo() {
        return saldo;
    }
}
