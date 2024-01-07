import java.io.Serializable;

public abstract class Utilizador implements Serializable {
    /**
     * @author César Temudo
     * @author Vânia Mendes
     * @version 1.0
     */

    protected String username;
    protected String password;
    protected double saldo;

    /**
     * Construtor geral de um utilizador.
     * @param username
     * @param password
     */
    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Método usado para distinguir de forma rápida qual o tipo de utilizador.
     * @return tipo de utilizador
     */
    public abstract int getTipo();

    /**
     * Altera o saldo atual mediante a quantia introduzida.
     * @param quantia
     */
    public void alterarSaldo(double quantia) {
        saldo += quantia;
    }
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