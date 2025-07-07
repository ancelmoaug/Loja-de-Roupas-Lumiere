import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente {

    private String CPF;
    private Date dataNascimento;
    private String contato;
    private String endereco;

    private static List<Cliente> clientes = new ArrayList<>();

 
    public Cliente(String CPF, Date dataNascimento, String contato, String endereco) {
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.contato = contato;
        this.endereco = endereco;
    }


    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static void atualizarCliente(String cpf, Cliente novoCliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).CPF.equals(cpf)) {
                clientes.set(i, novoCliente);
                break;
            }
        }
    }

    public static void removerCliente(String cpf) {
        clientes.removeIf(cliente -> cliente.CPF.equals(cpf));
    }

    public static void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public static Cliente pesquisarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.CPF.equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "CPF='" + CPF + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", contato='" + contato + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

  
}

